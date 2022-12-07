package cn.udday.schoolbus.service.impl;

import cn.udday.schoolbus.mapper.TokenMapper;
import cn.udday.schoolbus.mapper.UserMapper;
import cn.udday.schoolbus.model.PageResponse;
import cn.udday.schoolbus.model.Response;
import cn.udday.schoolbus.model.Token;
import cn.udday.schoolbus.model.User;
import cn.udday.schoolbus.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static cn.udday.schoolbus.config.Config.*;

@Service("user")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private TokenMapper tokenMapper;
    //  配置logger
    private final static Logger logger = LoggerFactory.getLogger(UserService.class);
    private final static String text = "用户中心";

    public Object getUserList() {
        try {
            List<User> users = userMapper.selectList(null);
            logger.info("[{}]:: 查询所有{}信息 >>> 查询成功 {}", projectName, text, users);
            return new Response<>(users);
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "未查询到{}！", text);
            logger.error("[{}]::查询所有{}信息 >>> 查询失败！{}", projectName, text, response);
            return response;
        }
    }

    public Object queryUser(Map<String, String> data) {
        String mode = data.get("mode");
        String options = data.get("options");
        logger.info("[{}]:: 查询{}信息:: 查询模式-> " + mode + " 查询参数->" + options, projectName, text);
        IPage<User> page = new Page<>(Integer.parseInt(data.get("pageIndex")), Integer.parseInt(data.get("pageSize")));
        try {
            if ("all".equals(options)) {
                userMapper.selectPage(page, null);
                List<User> users = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]getUserInfo:: 查询所有{}信息 >>> 查询成功", projectName, text);
                return new PageResponse<>(users, pageTotal);
            } else if ("id".equals(mode)) {
                User user = userMapper.selectById(options);
                List<User> users = new ArrayList<>();
                users.add(user);
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功 {}", projectName, text, mode, users);
                return new Response<>(users);
            } else if ("name".equals(mode)) {
                QueryWrapper<User> wrapper = new QueryWrapper<>();
                wrapper.eq("user_name", options);
                userMapper.selectPage(page, wrapper);
                List<User> users = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功", projectName, mode, text);
                return new PageResponse<>(users, pageTotal);
            } else {
                Response<Object> response = new Response<>(-2, "查询模式错误！");
                logger.error("[{}]:: 查询所有{}信息 >>> 查询失败 [{}]", projectName, text, response);
                return response;
            }
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "查询失败！");
            logger.error("[{}]::查询{}信息 >>> 查询失败！[{}]", projectName, text, e);
            return response;
        }
    }

    @Override
    public Object login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        if (userMapper.selectList(queryWrapper).isEmpty()) {
            return Response.error(1,"用户不存在");
        }
        User user = userMapper.selectOne(queryWrapper);
        if (user.getPassword().isEmpty() && !user.getPassword().equals(password)) {
            return Response.error(2,"密码错误");
        }
        //设置token
        Token token = tokenMapper.findByUserId(user.getUserId());
        Date date = new Date();
        Long nowtime = date.getTime() / 1000;
        String tokenStr = creatToken(user, date);
        if (token == null){
            token = new Token();
            token.setToken(tokenStr);
            token.setBuildTime(nowtime);
            token.setUserId(user.getUserId());
            tokenMapper.insert(token);
        }else{
            token.setToken(tokenStr);
            token.setBuildTime(nowtime);
            QueryWrapper<Token> qwToken = new QueryWrapper<>();
            qwToken.eq("user_id",user.getUserId());
            tokenMapper.update(token,qwToken);
        }
        HashMap<String, String> resMap = new HashMap();
        resMap.put("token", token.getToken());
        return new Response(resMap, "登陆成功");
    }

    @Override
    public Object register(String username, String password, String sex, String phone, Boolean isSuper) {
        User user = new User(username, password, sex, phone, isSuper, "");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        if (!userMapper.selectList(queryWrapper).isEmpty()) {
            return Response.error("用户名重复");
        }
        userMapper.insert(user);
        User newUser = userMapper.selectOne(queryWrapper);
        Date date = new Date();
        Long nowtime = date.getTime() / 1000;
        String tokenStr = creatToken(user, date);
        Token token = new Token();
        token.setToken(tokenStr);
        token.setBuildTime(nowtime);
        token.setUserId(newUser.getUserId());
        tokenMapper.insert(token);
        HashMap<String, String> resMap = new HashMap();
        resMap.put("token", token.getToken());
        return new Response(resMap, "注册成功");
    }


    private String creatToken(User user, Date date) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT") // 设置header
                .setHeaderParam("alg", "HS256").setIssuedAt(date) // 设置签发时间
                .setExpiration(new Date(date.getTime() + 1000 * 60 * 60 * 24 * 3))
                .claim("userid", String.valueOf(user.getUserId())) // 设置内容
                .setIssuer(ISSUER)// 设置签发人
                .signWith(signatureAlgorithm, KEY); // 签名，需要算法和key
        String jwt = builder.compact();
        return jwt;
    }
}

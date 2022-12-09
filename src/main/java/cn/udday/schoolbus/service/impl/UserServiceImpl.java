package cn.udday.schoolbus.service.impl;

import cn.udday.schoolbus.SchoolBusUtils;
import cn.udday.schoolbus.mapper.TokenMapper;
import cn.udday.schoolbus.mapper.UserMapper;
import cn.udday.schoolbus.model.*;
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

    @Override
    public Object login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        if (userMapper.selectList(queryWrapper).isEmpty()) {
            return Response.error(1,"用户不存在");
        }
        User user = userMapper.selectOne(queryWrapper);
        if (user.getPassword().isEmpty() || !user.getPassword().equals(password)) {
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

    @Override
    public Object loginOut(String token) {
        int userId = SchoolBusUtils.token2UserId(token);
        QueryWrapper<Token> tokenQW = new QueryWrapper<>();
        tokenQW.eq("user_id",userId);
        tokenMapper.delete(tokenQW);
        return Response.ok("成功退出登陆");
    }

    @Override
    public Object all(int pageNum,int pageSize,String userName) {
        Page<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> userQW = new QueryWrapper<>();
        userQW.like("user_name",userName);
        userMapper.selectPage(page,userQW);
        return new PageResponse<>(page.getRecords(), (int) page.getPages());
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

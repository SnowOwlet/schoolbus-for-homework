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
    public Object change(String token,String password, String sex, String phone,String comment) {
        User user = userMapper.getUserById(SchoolBusUtils.token2UserId(token));
        if (password != null && !password.equals("")) user.setPassword(password);
        if (sex != null && !sex.equals("")) user.setAvatar(sex);
        if (phone != null && !phone.equals("")) user.setPhone(phone);
        if (comment != null && !comment.equals("")) user.setComment(comment);
        userMapper.updateById(user);
        return Response.ok("更改成功");
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

    @Override
    public Object one(String token) {
        int userId = SchoolBusUtils.token2UserId(token);
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("user_id",userId);
        User user = userMapper.selectOne(qw);
        if (user != null){
            return new Response(user);
        }else{
            return Response.error("不存在该用户");
        }
    }


    private String creatToken(User user, Date date) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256").setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + 1000 * 60 * 60 * 24 * 3))
                .claim("user_id", String.valueOf(user.getUserId()))
                .claim("is_super", String.valueOf(user.isSuper()))
                .claim("user_name", String.valueOf(user.getUserName()))
                .setIssuer(ISSUER)
                .signWith(signatureAlgorithm, KEY);
        String jwt = builder.compact();
        return jwt;
    }
}

package cn.udday.schoolbus.service;

import cn.udday.schoolbus.model.User;

import java.util.Map;

public interface UserService {
    Object login(String username,String password);
    Object register(String username,String password,String sex,String phone,Boolean isSuper);

    Object change(String token,String password,String sex,String phone,String comment);

    Object loginOut(String token);

    Object all(int pageNum,int pageSize,String username);

    Object one(String token);
}

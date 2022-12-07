package cn.udday.schoolbus.service;

import cn.udday.schoolbus.model.User;

import java.util.Map;

public interface UserService {
    Object login(String username,String password);
    Object register(String username,String password,String sex,String phone,Boolean isSuper);
}

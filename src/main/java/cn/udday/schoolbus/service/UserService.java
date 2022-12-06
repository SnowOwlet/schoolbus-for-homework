package cn.udday.schoolbus.service;

import java.util.Map;

public interface UserService {
    Object getUserList();

    Object getUserListAssociated();

    Object queryUser(Map<String, String> data);

    Object queryUserAssociated(Map<String, String> data);
}

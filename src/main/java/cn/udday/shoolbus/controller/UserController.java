package cn.udday.shoolbus.controller;

import cn.udday.shoolbus.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
public class UserController {

    @Resource
    private UserService userService;
    @GetMapping(value = "/user/getUserList")
    public Object getUserList() {
        Object res = userService.getUserList();
        return res;
    }

    @GetMapping(value = "/user/getUserListAssociated")
    public Object getUserListAssociated() {
        Object res = userService.getUserListAssociated();
        return res;
    }

    @GetMapping(value = "/user/queryUser")
    public Object queryUser(@RequestParam Map<String, String> data) {
        Object res = userService.queryUser(data);
        return res;
    }

    @GetMapping(value = "/user/queryUserAssociated")
    public Object queryUserAssociated(@RequestParam Map<String, String> data) {
        Object res = userService.queryUserAssociated(data);
        return res;
    }
}

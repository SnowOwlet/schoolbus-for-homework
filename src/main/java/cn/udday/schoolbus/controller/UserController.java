package cn.udday.schoolbus.controller;

import cn.udday.schoolbus.model.Response;
import cn.udday.schoolbus.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

//@CrossOrigin
@RestController
@RequestMapping("/API/user/")
public class UserController {

    @Resource(name = "user")
    private UserService userService;

    @PostMapping(value = "/login")
    public Object login(@RequestBody Map<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");
        if (username.equals("") || password.equals("")){
            return Response.error("账号密码不能为空");
        }
        Object res = userService.login(username,password);
        return res;
    }

    @PostMapping(value = "/register")
    public Object register(@RequestBody Map<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");
        String sex = data.get("sex");
        String phone = data.get("phone");
        Boolean isSuper = Boolean.valueOf(data.get("is_super"));

        if (username.equals("") ||password.equals("")){
            return Response.error("账号密码不能为空");
        }
        if (sex.equals("")){ return Response.error("性别不能为空"); }

        Object res = userService.register(username,password,sex,phone,isSuper);
        return res;
    }
}

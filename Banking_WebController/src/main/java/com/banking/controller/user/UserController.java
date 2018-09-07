package com.banking.controller.user;

import com.banking.common.vo.R;
import com.banking.domain.user.User;
import com.banking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //登录
    @RequestMapping("/userlogin.do")
    public R userLogin(String username,String password){
        return userService.userLogin(username,password);
    }

    //注册
    @RequestMapping("/register.do")
    public R register(User user){
        System.out.println(user);
        return userService.register(user);
    }
}

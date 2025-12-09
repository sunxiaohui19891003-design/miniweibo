package com.example.miniweibo.controller;

import com.example.miniweibo.entity.User;
import com.example.miniweibo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }
    @PostMapping("/login")
    public Map<String,Object> login (@RequestBody User user, HttpSession session){
        User dbUser = userService.login(user);
        if(dbUser == null ){
            return Map.of("message","用户名或密码错误");
        }
        session.setAttribute("userId",dbUser.getId());
        return Map.of(
                "message", "登录成功",
                "userId", dbUser.getId(),
                "username", dbUser.getUsername()
        );
    }
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteById(id);
    }
    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "退出登录";
    }
}

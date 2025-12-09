package com.example.miniweibo.controller;

import com.example.miniweibo.entity.User;
import com.example.miniweibo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }
    @PostMapping("/login")
    public User login(@RequestBody User user){
        return userService.login(user);
    }
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteById(id);
    }
}

package com.example.miniweibo.controller;

import com.example.miniweibo.entity.User;
import com.example.miniweibo.entity.Weibo;
import com.example.miniweibo.repository.UserRepository;
import com.example.miniweibo.repository.WeiboRepository;
import com.example.miniweibo.service.WeiboService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController

public class WeiboController {
    @Autowired
    private WeiboService weiboService;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/post")
    public Weibo post(@RequestBody Weibo weibo, HttpSession session){
        Long userId = (Long)session.getAttribute("userId");
        if(userId == null ){
            throw new RuntimeException("请先登录");
        }
        User user = userRepository.findById(userId).orElse(null);
        weibo.setUser(user);
        weibo.setCreateTime(LocalDateTime.now());
        return weiboService.post(weibo);
    }
    @PostMapping("/weibo/list")
    public List<Weibo> list(){
        return weiboService.list();
    }

    @PostMapping("/user/{userId}")
    public List<Weibo> listByUserId(@PathVariable Long userId){
        return weiboService.listByUserId(userId);
    }
    @DeleteMapping("/{id}")
    public void deleteById (@PathVariable Long id){
        weiboService.deleteById(id);
    }
    @PutMapping("/weibo/{id}")
    public Weibo update(@PathVariable Long id,@RequestBody Weibo weibo){
        return weiboService.update(id,weibo.getContent());
    }
}

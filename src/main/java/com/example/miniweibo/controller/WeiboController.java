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
    @PostMapping("/weibo/search")
    public List<Weibo> search(@RequestParam String keyword) {
        return weiboService.searchByContent(keyword);
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
    public void deleteById (@PathVariable Long id,HttpSession session){
        Long loginUserId = (Long)session.getAttribute("userId");
        if(loginUserId == null){
            throw new RuntimeException("请先登录");
        }
        Weibo weibo = weiboService.findById(id);
        if(weibo == null){
            throw new RuntimeException("微博不存在");
        }
        if(!weibo.getUser().getId().equals(loginUserId)){
            throw new RuntimeException("不能删除别人的微博");
        }
        weiboService.deleteById(id);
    }
    @PutMapping("/weibo/{id}")
    public Weibo update(@PathVariable Long id,@RequestBody Weibo weibo,HttpSession session){
        Long loginUserId = (Long)session.getAttribute("userId");
        if(loginUserId == null){
            throw new RuntimeException("请先登录");
        }
        Weibo oldWeibo = weiboService.findById(id);
        if(oldWeibo == null){
            throw new RuntimeException("微博不存在");
        }
        if(!oldWeibo.getUser().getId().equals(loginUserId)){
            throw new RuntimeException("不能修改别人的微博");
        }
        return weiboService.update(id,weibo.getContent());
    }
    @PostMapping("/weibo/{id}/like")
    public boolean like (@PathVariable Long id, HttpSession session){
        Long loginUserId = (Long)session.getAttribute("userId");
        if(loginUserId == null){
            return false;
        }
        return weiboService.like(loginUserId,id);
    }
}

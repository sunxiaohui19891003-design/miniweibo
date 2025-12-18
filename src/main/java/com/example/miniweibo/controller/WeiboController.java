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
        Long loginUserId = (Long)session.getAttribute("userId");
        if(loginUserId == null ){
            throw new RuntimeException("ログインしてください");
        }
        return weiboService.post(weibo,loginUserId);
    }
    @PostMapping("/weibo/search")
    public List<Weibo> search(@RequestParam String keyword) {
        return weiboService.searchByContent(keyword);
    }

    @PostMapping("/weibo/list")
    public List<Weibo> list(){
        return weiboService.list();
    }

    @GetMapping("/weibo/my")
    public List<Weibo> listByUserId(HttpSession session){
        Long loginUserId = (Long)session.getAttribute("userId");
        if(loginUserId == null){
            throw new RuntimeException("ログインしてください。");
        }
        return weiboService.listByUserId(loginUserId);
    }
    @DeleteMapping("/{id}")
    public void deleteById (@PathVariable Long id,HttpSession session){
        Long loginUserId = (Long)session.getAttribute("userId");
        if(loginUserId == null){
            throw new RuntimeException("ログインしてください。");
        }
        weiboService.deleteById(id,loginUserId);
    }
    @PutMapping("/weibo/{id}")
    public Weibo update(@PathVariable Long id,@RequestBody Weibo weibo,HttpSession session){
        Long loginUserId = (Long)session.getAttribute("userId");
        if(loginUserId == null){
            throw new RuntimeException("ログインしてください。");
        }
        return weiboService.update(id,weibo.getContent(),loginUserId);
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

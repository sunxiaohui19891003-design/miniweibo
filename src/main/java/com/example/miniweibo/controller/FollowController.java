package com.example.miniweibo.controller;

import com.example.miniweibo.entity.Follow;
import com.example.miniweibo.entity.User;
import com.example.miniweibo.service.FollowService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FollowController {
    @Autowired
    FollowService followService;
    @PostMapping("/follow")
    public boolean follow (@RequestParam Long following_id, HttpSession session){
        Long follower_id = (Long)session.getAttribute("userId");
        return followService.follow(follower_id,following_id);
    }
    @GetMapping("/followings")
    public List<User> followings (HttpSession session){
        Long follower_id = (Long)session.getAttribute("userId");
        return followService.getFollowings(follower_id);
    }
    @GetMapping("/followers")
    public List<User> followers (HttpSession session){
        Long follower_id = (Long)session.getAttribute("userId");
        return followService.getFollowers(follower_id);
    }

}

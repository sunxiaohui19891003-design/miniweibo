package com.example.miniweibo.controller;

import com.example.miniweibo.entity.Favorite;
import com.example.miniweibo.entity.Weibo;
import com.example.miniweibo.service.FavoriteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;
    @PostMapping("/favorites")
    public boolean addFavorite (HttpSession httpSession, Long weiboId){
        Long userId = (Long)httpSession.getAttribute("userId");
        return favoriteService.addFavorite(userId,weiboId);
    }
    @GetMapping("/favorites")
    public List<Weibo> zhaoFavorites(HttpSession httpSession,Long weiboId){
        Long userId = (Long)httpSession.getAttribute("userId");
        return favoriteService.zhaoFavorites(userId,weiboId);
    }
}

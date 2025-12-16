package com.example.miniweibo.service;

import com.example.miniweibo.entity.Favorite;
import com.example.miniweibo.entity.Weibo;
import com.example.miniweibo.repository.FavoriteRepository;
import com.example.miniweibo.repository.WeiboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {
    @Autowired
    FavoriteRepository favoriteRepository;
    @Autowired
    WeiboRepository weiboRepository;
    @Autowired
    ViewHistoryService viewHistoryService;
    public boolean addFavorite (Long userId,Long weiboId){
        Optional<Favorite> count = favoriteRepository.findByUserIdAndWeiboId(userId,weiboId);
        if(count.isPresent()){
            favoriteRepository.delete(count.get());
            return false;
        }else {
            Favorite favorite = new Favorite();
            favorite.setUserId(userId);
            favorite.setWeiboId(weiboId);
            favoriteRepository.save(favorite);
        }
        viewHistoryService.addRecordView(
                userId,     // 谁浏览的
                weiboId     // 浏览了哪条微博
        );
        return true;
    }

    public List<Weibo> zhaoFavorites(Long userId, Long weiboId) {
        List<Favorite> zhaoFavorites = favoriteRepository.findByUserId(userId);
        List<Long> weiboIds = new ArrayList<>();
        for(Favorite f : zhaoFavorites){
            weiboIds.add(f.getWeiboId());
        }
        return weiboRepository.findAllById(weiboIds);
    }
}

package com.example.miniweibo.service;

import com.example.miniweibo.entity.Favorite;
import com.example.miniweibo.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteService {
    @Autowired
    FavoriteRepository favoriteRepository;
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
        return true;
    }
}

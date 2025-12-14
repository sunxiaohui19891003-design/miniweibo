package com.example.miniweibo.service;

import com.example.miniweibo.entity.Weibo;
import com.example.miniweibo.entity.WeiboLike;
import com.example.miniweibo.repository.WeiboLikeRepository;
import com.example.miniweibo.repository.WeiboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WeiboService {
    @Autowired
    private WeiboRepository weiboRepository;
    @Autowired
    private WeiboLikeRepository weiboLikeRepository;
    public Weibo post(Weibo weibo) {
        weibo.setCreateTime(LocalDateTime.now());
        return weiboRepository.save(weibo);
    }

    public List<Weibo> list() {

        return weiboRepository.findAll();
    }
    public Weibo findById(Long id){
        return  weiboRepository.findById(id).orElse(null);
    }


    public List<Weibo> listByUserId(Long userId) {
        return weiboRepository.findByUserId(userId);
    }

    public void deleteById (Long id) {

        weiboRepository.deleteById(id);
    }
    public Weibo update(Long id,String newContent){
        Weibo weibo = weiboRepository.findById(id).orElse(null);
        if(weibo == null){ return null;}
        weibo.setContent(newContent);
        return weiboRepository.save(weibo);
    }
    public boolean like(Long userId,Long id){
        Weibo weibo = weiboRepository.findById(id).orElse(null);
        if(weibo == null){
            return false;
        }
        Optional<WeiboLike> count = weiboLikeRepository.findByUserIdAndWeiboId(userId,id);
        if(count.isPresent()){
            WeiboLike like = count.get();
            weiboLikeRepository.delete(like);
            int n = weibo.getLikeCount();
            weibo.setLikeCount(n - 1);
            weiboRepository.save(weibo);
            return false;
        }else{
            WeiboLike xinLike =  new WeiboLike();
            xinLike.setUserId(userId);
            xinLike.setWeiboId(id);
;           weiboLikeRepository.save(xinLike);
            int n = weibo.getLikeCount();
            weibo.setLikeCount(n + 1);
            weiboRepository.save(weibo);
            return true;
        }
    }
}

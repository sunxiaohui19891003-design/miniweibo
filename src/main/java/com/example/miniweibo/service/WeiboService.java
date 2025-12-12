package com.example.miniweibo.service;

import com.example.miniweibo.entity.Weibo;
import com.example.miniweibo.repository.WeiboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class WeiboService {
    @Autowired
    private WeiboRepository weiboRepository;
    public Weibo post(Weibo weibo) {
        weibo.setCreateTime(LocalDateTime.now());
        return weiboRepository.save(weibo);
    }

    public List<Weibo> list() {
        return weiboRepository.findAll();
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

}

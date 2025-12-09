package com.example.miniweibo.repository;

import com.example.miniweibo.entity.Weibo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeiboRepository extends JpaRepository<Weibo,Long> {
    List<Weibo> findByUserId(Long userID);

}

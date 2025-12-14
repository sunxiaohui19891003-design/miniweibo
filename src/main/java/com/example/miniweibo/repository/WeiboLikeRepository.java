package com.example.miniweibo.repository;

import com.example.miniweibo.entity.WeiboLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeiboLikeRepository extends JpaRepository<WeiboLike,Long> {
    Optional<WeiboLike> findByUserIdAndWeiboId(Long userId, Long weiboId);
}

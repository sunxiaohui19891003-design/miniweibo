package com.example.miniweibo.repository;

import com.example.miniweibo.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    Optional<Favorite> findByUserIdAndWeiboId(Long userId, Long weiboId);
}

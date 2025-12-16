package com.example.miniweibo.repository;

import com.example.miniweibo.entity.Follow;
import com.example.miniweibo.entity.WeiboLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    Optional<Follow> findByFollower_IdAndFollowing_Id(Long followerId, Long followingId);

    List<Follow> findByFollower_Id(Long followerId);

    List<Follow> findByFollowing_Id(Long followerId);
}

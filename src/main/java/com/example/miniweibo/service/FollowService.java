package com.example.miniweibo.service;

import com.example.miniweibo.entity.Follow;
import com.example.miniweibo.entity.User;
import com.example.miniweibo.entity.Weibo;
import com.example.miniweibo.entity.WeiboLike;
import com.example.miniweibo.repository.FollowRepository;
import com.example.miniweibo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class FollowService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FollowRepository followRepository;

    public boolean follow(Long follower_id, Long following_id) {
        Optional<Follow> count =
                followRepository.findByFollower_IdAndFollowing_Id(follower_id, following_id);
        if (count.isPresent()) {
            Follow follow = count.get();
            followRepository.delete(follow);
            return false;
        } else {
            User follower = userRepository.findById(follower_id).get();
            User following = userRepository.findById(following_id).get();
            Follow xinFollow = new Follow();
            xinFollow.setFollower(follower);
            xinFollow.setFollowing(following);
            followRepository.save(xinFollow);
            return true;
        }
    }
    public List<User> getFollowings (Long follower_id){
        List<Follow> follows = followRepository.findByFollower_Id(follower_id);
        return follows.stream().map(Follow::getFollowing).toList();
    }
    public List<User> getFollowers (Long follower_id){
        List<Follow> follows = followRepository.findByFollowing_Id(follower_id);
        return follows.stream().map(Follow::getFollower).toList();
    }
}

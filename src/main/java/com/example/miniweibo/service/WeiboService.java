package com.example.miniweibo.service;

import com.example.miniweibo.entity.User;
import com.example.miniweibo.entity.Weibo;
import com.example.miniweibo.entity.WeiboLike;
import com.example.miniweibo.repository.UserRepository;
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
    @Autowired
    NotificationService notificationService;
    @Autowired
    ViewHistoryService viewHistoryService;
    @Autowired
    UserRepository userRepository;

    public Weibo post(Weibo weibo,Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->new RuntimeException("ユーザーが存在しません"));
        weibo.setUser(user);
        weibo.setCreateTime(LocalDateTime.now());
        return weiboRepository.save(weibo);
    }

    public List<Weibo> list() {

        return weiboRepository.findAll();
    }

    public Weibo findById(Long id) {
        return weiboRepository.findById(id).orElse(null);
    }


    public List<Weibo> listByUserId(Long userId) {
        return weiboRepository.findByUserId(userId);
    }

    public void deleteById(Long id, Long userId) {
        Weibo weibo = weiboRepository.findById(id).orElseThrow(() -> new RuntimeException("投稿が存在しません。"));
        User user = weibo.getUser();
        Long nowId = user.getId();
        if (!userId.equals(nowId)) {
            throw new RuntimeException("自分の投稿しか削除できません。");
        }
            weiboRepository.deleteById(id);
    }

    public Weibo update(Long id, String newContent,Long loginUserId) {
        Weibo weibo = weiboRepository.findById(id).orElseThrow(() -> new RuntimeException("投稿が存在しません。"));

        User user = weibo.getUser();
        Long nowId = user.getId();
        if (!loginUserId.equals(nowId)) {
            throw new RuntimeException("他人の投稿は更新できません。");
        }
        weibo.setContent(newContent);
        return weiboRepository.save(weibo);
    }

    public List<Weibo> searchByContent(String keyword) {
        return weiboRepository.findByContentContaining(keyword);
    }

    public boolean like(Long userId, Long id) {
        Weibo weibo = weiboRepository.findById(id).orElse(null);
        if (weibo == null) {
            return false;
        }
        Optional<WeiboLike> count = weiboLikeRepository.findByUserIdAndWeiboId(userId, id);
        if (count.isPresent()) {
            WeiboLike like = count.get();
            weiboLikeRepository.delete(like);
            int n = weibo.getLikeCount();
            weibo.setLikeCount(n - 1);
            weiboRepository.save(weibo);
            return false;
        } else {
            WeiboLike xinLike = new WeiboLike();
            xinLike.setUserId(userId);
            xinLike.setWeiboId(id);
            WeiboLike saved = weiboLikeRepository.save(xinLike);
            int n = weibo.getLikeCount();
            weibo.setLikeCount(n + 1);
            weiboRepository.save(weibo);
            notificationService.addnotification(
                    userId,                       // 谁点的赞
                    weibo.getUser().getId(),      // 通知给微博作者
                    "LIKE",                       // 类型
                    id                            // targetId：微博ID
            );
            viewHistoryService.addRecordView(
                    userId,     // 谁浏览的
                    weibo.getId()     // 浏览了哪条微博
            );
            return true;
        }
    }
}

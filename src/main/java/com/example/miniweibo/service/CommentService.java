package com.example.miniweibo.service;

import com.example.miniweibo.entity.Comment;
import com.example.miniweibo.entity.User;
import com.example.miniweibo.entity.Weibo;
import com.example.miniweibo.repository.CommentRepository;
import com.example.miniweibo.repository.WeiboRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private WeiboRepository weiboRepository;
    @Autowired
    NotificationService notificationService;
    @Autowired
    ViewHistoryService viewHistoryService;

    public Comment addComment(User user, Long weiboId, String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        Weibo weibo = weiboRepository.findById(weiboId).get();
        comment.setWeibo(weibo);
        Comment saved = commentRepository.save(comment);
        notificationService.addnotification(
                user.getId(),          // 谁发的
                weibo.getUser().getId(),        // 通知给谁
                "COMMENT",         // 类型
                saved.getId()    // 关联的私信ID
        );
        viewHistoryService.addRecordView(
                user.getId(),     // 谁浏览的
                weibo.getId()     // 浏览了哪条微博
        );
        return saved;
    }

    public void deleteComment(User user, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("コメントが存在しません。"));
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("他人のコメントは削除できません。");
        }
        commentRepository.delete(comment);
    }

    public Comment updateComment(User user, Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("コメントが存在しません。"));
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("他人のコメントは更新できません。");
        }

        if (content == null || content.trim().isEmpty()) {
            throw new RuntimeException("コメント内容を入力してください。");

        }
        comment.setContent(content);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByWeibo(Long weiboId) {
        Weibo weibo = weiboRepository.findById(weiboId).orElseThrow(()->new RuntimeException("投稿が存在しません。"));
        return commentRepository.findByWeibo(weibo);
    }
}

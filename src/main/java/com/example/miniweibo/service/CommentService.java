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
    public Comment addComment(User user, Long weiboId, String content){
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
        return saved;
    }
    public void deleteComment(User user,Long commentId){
        Comment comment = commentRepository.findById(commentId).get();
        if(comment.getUser().getId().equals (user.getId())){
            commentRepository.delete(comment);
            return;
        }
    }
    public Comment updateComment(User user,Long commentId,String content){
        Comment comment = commentRepository.findById(commentId).get();
        if(!comment.getUser().getId().equals (user.getId())){
            return null;
        }
        comment.setContent(content);
        commentRepository.save(comment);
        return comment;
    }
    public List<Comment> getCommentsByWeibo(Long weiboId){
        Weibo weibo = weiboRepository.findById(weiboId).get();
        List<Comment> comments = commentRepository.findByWeibo(weibo);
        return comments;
    }
}

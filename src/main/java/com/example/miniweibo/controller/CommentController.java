package com.example.miniweibo.controller;

import com.example.miniweibo.entity.AddCommentRequest;
import com.example.miniweibo.entity.Comment;
import com.example.miniweibo.entity.User;
import com.example.miniweibo.entity.Weibo;
import com.example.miniweibo.repository.UserRepository;
import com.example.miniweibo.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.AbstractDocument;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/addComment")
    public Comment addComment(@RequestBody AddCommentRequest addCommentRequest, HttpSession httpSession){
        Long userId = (Long) httpSession.getAttribute("userId");
        if(userId == null){
            throw new RuntimeException("ログインしてください。");
        }
        User user = userRepository.findById(userId).get();
        return commentService.addComment(user,addCommentRequest.getWeiboId(),addCommentRequest.getContent());
    }
    @PostMapping("/deleteComment")
    public void deleteComment(HttpSession httpSession,@RequestParam Long commentId){
        Long userId = (Long) httpSession.getAttribute("userId");
        if(userId == null){
            throw new RuntimeException("ログインしてください。");
        }
        User user = userRepository.findById(userId).get();
        commentService.deleteComment(user,commentId);
    }
    @PutMapping("/comment/{commentId}")
    public Comment updateComment(HttpSession httpSession,
                                 @PathVariable Long commentId,
                                 @RequestBody AddCommentRequest addCommentRequest){
        Long userId = (Long) httpSession.getAttribute("userId");
        if(userId == null){
            throw new RuntimeException("ログインしてください。");
        }
        User user = userRepository.findById(userId).orElseThrow();
        return commentService.updateComment(user,commentId,addCommentRequest.getContent());
    }
    @GetMapping("/getCommentsByWeibo")
    public List<Comment> getCommentsByWeibo(@RequestParam Long weiboId,HttpSession httpSession){
        Long userId = (Long) httpSession.getAttribute("userId");
        if (userId == null) {
            throw new RuntimeException("ログインしてください。");
        }
        return commentService.getCommentsByWeibo(weiboId);
    }
}

package com.example.miniweibo.repository;

import com.example.miniweibo.entity.Comment;
import com.example.miniweibo.entity.Weibo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByWeibo(Weibo weibo);
}

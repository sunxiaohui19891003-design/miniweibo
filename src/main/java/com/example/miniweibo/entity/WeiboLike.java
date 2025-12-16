package com.example.miniweibo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "weibo_like")
public class WeiboLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long weiboId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(Long weiboId) {
        this.weiboId = weiboId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}

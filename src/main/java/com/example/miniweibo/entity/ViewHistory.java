package com.example.miniweibo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "view_history")
public class ViewHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "weibo_id", nullable = false)
    private Long weiboId;

    @Column(name = "view_time", nullable = false, insertable = false, updatable = false)
    private LocalDateTime viewTime;

    /* ===== getter / setter ===== */

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(Long weiboId) {
        this.weiboId = weiboId;
    }

    public LocalDateTime getViewTime() {
        return viewTime;
    }
}

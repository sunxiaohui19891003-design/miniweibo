package com.example.miniweibo.entity;

public class AddCommentRequest {
    private String content;
    private Long weiboId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(Long weiboId) {
        this.weiboId = weiboId;
    }
}

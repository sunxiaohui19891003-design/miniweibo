package com.example.miniweibo.service;

import com.example.miniweibo.entity.Comment;
import com.example.miniweibo.entity.Notification;
import com.example.miniweibo.entity.User;
import com.example.miniweibo.entity.Weibo;
import com.example.miniweibo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    public Notification addnotification(Long fromUserId,Long userId,String type,Long targetId){
        Notification notification = new Notification();
        notification.setFromUserId(fromUserId);
        notification.setUserId(userId);
        notification.setType(type);
        notification.setTargetId(targetId);
        notification.setRead(false);
        return notificationRepository.save(notification);
    }
}

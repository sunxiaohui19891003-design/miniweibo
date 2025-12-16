package com.example.miniweibo.controller;

import com.example.miniweibo.entity.Notification;
import com.example.miniweibo.entity.User;
import com.example.miniweibo.repository.NotificationRepository;
import com.example.miniweibo.service.NotificationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    NotificationService notificationService;
    @Autowired
    NotificationRepository notificationRepository;
    @PostMapping("/notifications")
    public Notification addnotification(Long fromUserId, HttpSession httpSession, String type, Long targetId){
        Long userId = (Long) httpSession.getAttribute("userId");
        return notificationService.addnotification(fromUserId,userId,type,targetId);
    }
    @GetMapping("/notifications")
    public List<Notification> notificationLists(HttpSession httpSession){
        Long userId = (Long) httpSession.getAttribute("userId");
        return notificationRepository.findByUserIdOrderByIdDesc(userId);
    }
}

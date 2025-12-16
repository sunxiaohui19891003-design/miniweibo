package com.example.miniweibo.controller;

import com.example.miniweibo.entity.Message;
import com.example.miniweibo.entity.User;
import com.example.miniweibo.service.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    MessageService messageService;
    @PostMapping("/addMessage")
    public Message addMessage(HttpSession httpsession, Long receiverId, String content){
        Long senderId = (Long)httpsession.getAttribute("userId");
        return messageService.addMessage(senderId,receiverId,content);
    }
    @PostMapping("/deleteMessage")
    public  void deleteMessage(Long id,HttpSession httpsession){
        Long senderId = (Long)httpsession.getAttribute("userId");
        messageService.deleteMessage(id,senderId);
    }
    @PostMapping("/updateMessage")
    public  Message updateMessage(Long id,HttpSession httpsession,String content){
        Long senderId = (Long)httpsession.getAttribute("userId");
            return messageService.updateMessage(id,senderId,content);
    }
    @PostMapping("/messages")
    public  List<Message> messages(Long receiverId){
        return messageService.messages(receiverId);
    }
}

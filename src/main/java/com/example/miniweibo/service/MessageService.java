package com.example.miniweibo.service;

import com.example.miniweibo.entity.Message;
import com.example.miniweibo.entity.User;
import com.example.miniweibo.repository.MessageRepository;
import com.example.miniweibo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    NotificationService notificationService;
    public Message addMessage(Long senderId,Long receiverId,String content){
        Message message = new Message();
        message.setContent(content);
        User sender = userRepository.findById(senderId).get();
        message.setSender(sender);
        User receiver = userRepository.findById(receiverId).get();
        message.setReceiver(receiver);
        messageRepository.save(message);
        notificationService.addnotification(
                senderId,          // 谁发的
                receiverId,        // 通知给谁
                "MESSAGE",         // 类型
                message.getId()    // 关联的私信ID
        );
        return message;
    }
    public void deleteMessage(Long id,Long senderId){
        Message message = messageRepository.findById(id).get();
        if(senderId.equals(message.getSender().getId())){
            messageRepository.delete(message);
        }
    }
    public Message updateMessage(Long id,Long senderId,String content){
        Message message = messageRepository.findById(id).get();
        if(!senderId.equals(message.getSender().getId())){
            return null;
        }else{
            message.setContent(content);
            messageRepository.save(message);
            return message;
        }
    }
    public  List<Message> messages(Long receiverId){
        List<Message> messages = messageRepository.findByReceiver_Id(receiverId);
        return messages;
    }
}

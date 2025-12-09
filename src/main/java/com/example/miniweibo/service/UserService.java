package com.example.miniweibo.service;

import com.example.miniweibo.entity.User;
import com.example.miniweibo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User register(User user){
        return userRepository.save(user);
    }
    public User login(User user){
        User  dbUser = userRepository.findByUsername(user.getUsername());
        if(dbUser == null){
            return null;
        }
        if(!dbUser.getPassword().equals(user.getPassword())){
            return null;
        }

        return  dbUser;
    }
    public  void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
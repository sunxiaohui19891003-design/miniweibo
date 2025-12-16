package com.example.miniweibo.controller;

import com.example.miniweibo.entity.Weibo;
import com.example.miniweibo.service.ViewHistoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ViewHistoryController {

    @Autowired
    private ViewHistoryService viewHistoryService;

    @GetMapping("/view-history")
    public List<Weibo> getViewHistory(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        return viewHistoryService.weiboViewHistories(userId);
    }
}

package com.example.miniweibo.controller;

import com.example.miniweibo.entity.Report;
import com.example.miniweibo.service.ReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    @Autowired
    ReportService reportService;
    @PostMapping("/report")
    public Report addreport (HttpSession httpSession,
                             @RequestParam String targetType,
                             @RequestParam Long targetId,
                             @RequestParam String reasonType,
                             @RequestParam(required = false)String description){
        Long reporterId = (Long)httpSession.getAttribute("userId");
        if (reporterId == null) {
            throw new RuntimeException("未登录");
        }
        return reportService.addreport (reporterId, targetType,
                targetId, reasonType,
                description);
    }
}

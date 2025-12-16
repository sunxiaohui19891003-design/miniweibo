package com.example.miniweibo.service;

import com.example.miniweibo.entity.ViewHistory;
import com.example.miniweibo.entity.Weibo;
import com.example.miniweibo.repository.ViewHistoryRepository;
import com.example.miniweibo.repository.WeiboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewHistoryService {
    @Autowired
    ViewHistoryRepository viewHistoryRepository;
    @Autowired
    WeiboRepository weiboRepository;
    public void addRecordView(Long userId, Long weiboId) {
        ViewHistory viewHistory = new ViewHistory();
        viewHistory.setUserId(userId);
        viewHistory.setWeiboId(weiboId);
        viewHistoryRepository.save(viewHistory);
    }
    public List<Weibo> weiboViewHistories(Long userId){
        List<ViewHistory> viewHistories = viewHistoryRepository.findByUserId(userId);
        List<Long> weiboIds  = viewHistories.stream().map(ViewHistory::getWeiboId).toList();
        return weiboRepository.findAllById(weiboIds);
    }
}

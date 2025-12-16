package com.example.miniweibo.service;

import com.example.miniweibo.entity.Report;
import com.example.miniweibo.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;
    public Report addreport (Long reporterId,String targetType,
                             Long targetId,String reasonType,
                             String description){
        Report report = new Report();
        report.setReporterId(reporterId);
        report.setTargetType(targetType);
        report.setTargetId(targetId);
        report.setReasonType(reasonType);
        report.setDescription(description);
        report.setCreateTime(LocalDateTime.now());
        return reportRepository.save(report);
    }
}

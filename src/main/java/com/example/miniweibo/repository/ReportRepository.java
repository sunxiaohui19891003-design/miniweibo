package com.example.miniweibo.repository;

import com.example.miniweibo.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long> {
}

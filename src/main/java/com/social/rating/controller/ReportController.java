package com.social.rating.controller;

import com.social.rating.entity.Report;
import com.social.rating.service.ReportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/report-user")
    public Report reportUser(@RequestParam UUID userId,
                             @RequestParam String reason,
                             @RequestParam String description) {

        return reportService.reportUser(userId, reason, description);
    }
}

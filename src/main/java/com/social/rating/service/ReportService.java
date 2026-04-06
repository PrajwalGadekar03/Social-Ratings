package com.social.rating.service;

import com.social.rating.entity.Report;
import com.social.rating.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserService userService;

    public ReportService(ReportRepository reportRepository,
                         UserService userService) {
        this.reportRepository = reportRepository;
        this.userService = userService;
    }

    public Report reportUser(UUID reportedUserId, String reason, String description) {

        UUID reportedBy = userService.getLoggedInUser().getId();

        Report report = new Report();
        report.setReportedByUserId(reportedBy);
        report.setReportedUserId(reportedUserId);
        report.setReason(reason);
        report.setDescription(description);
        report.setStatus("OPEN");
        report.setCreatedAt(LocalDateTime.now());

        return reportRepository.save(report);
    }
}

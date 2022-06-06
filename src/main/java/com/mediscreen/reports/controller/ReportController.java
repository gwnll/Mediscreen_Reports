package com.mediscreen.reports.controller;

import com.mediscreen.reports.model.RiskLevel;
import com.mediscreen.reports.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("{patientId}/report/generate")
    public RiskLevel generateReport(@PathVariable int patientId) {
        return reportService.generateReport(patientId);
    }

}

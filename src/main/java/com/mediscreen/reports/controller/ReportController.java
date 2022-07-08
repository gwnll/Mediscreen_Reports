package com.mediscreen.reports.controller;

import com.mediscreen.reports.model.RiskLevel;
import com.mediscreen.reports.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    @Operation(summary = "Generate a report", responses = {
            @ApiResponse(responseCode = "200", description = "Report successfully generated"),
            @ApiResponse(responseCode = "404", description = "Not found / An error occurred")})
    @GetMapping("{patientId}/report")
    public RiskLevel generateReport(@PathVariable int patientId) {
        return reportService.generateReport(patientId);
    }

}

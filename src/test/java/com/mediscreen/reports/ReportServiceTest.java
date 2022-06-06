package com.mediscreen.reports;

import com.mediscreen.reports.model.RiskLevel;
import com.mediscreen.reports.service.ReportService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReportServiceTest {

    @Autowired
    ReportService reportService;

    @Test
    public void generateReport() {
        RiskLevel riskLevelNone = reportService.generateReport(1);
        Assertions.assertSame(riskLevelNone, RiskLevel.NONE);
        RiskLevel riskLevelBorderline = reportService.generateReport(51);
        Assertions.assertSame(riskLevelBorderline, RiskLevel.BORDERLINE);
        RiskLevel riskLevelDanger = reportService.generateReport(101);
        Assertions.assertSame(riskLevelDanger, RiskLevel.INDANGER);
        RiskLevel riskLevelOnSet = reportService.generateReport(151);
        Assertions.assertSame(riskLevelOnSet, RiskLevel.EARLYONSET);
    }

}

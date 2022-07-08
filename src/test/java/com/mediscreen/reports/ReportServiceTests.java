package com.mediscreen.reports;

import com.mediscreen.reports.model.Gender;
import com.mediscreen.reports.model.Patient;
import com.mediscreen.reports.model.RiskLevel;
import com.mediscreen.reports.service.ReportService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReportServiceTests {

    @Autowired
    ReportService reportService;

    @Test
    public void countTriggersOccurrences() {
        String observations = "Tests de laboratoire indiquant une microalbumine élevée";
        int triggersOccurrences = reportService.countTriggersOccurrences(observations);

        Assertions.assertEquals(1, triggersOccurrences);
    }

    @Test
    public void getRiskLevel() {
        int triggersOccurrences = 5;
        Patient patient = new Patient();
        patient.setBirthdate("15-03-1995");
        patient.setGender(Gender.MALE);

        RiskLevel riskLevel = reportService.getRiskLevel(triggersOccurrences, patient);
        Assertions.assertSame(riskLevel, RiskLevel.EARLYONSET);
    }

}

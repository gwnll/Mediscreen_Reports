package com.mediscreen.reports;

import com.mediscreen.reports.model.Gender;
import com.mediscreen.reports.model.Note;
import com.mediscreen.reports.model.Patient;
import com.mediscreen.reports.model.RiskLevel;
import com.mediscreen.reports.proxies.NotesProxy;
import com.mediscreen.reports.proxies.PatientsProxy;
import com.mediscreen.reports.service.ReportService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @Autowired
    ReportService reportService;

    @Mock
    NotesProxy notesProxy;

    @Mock
    PatientsProxy patientsProxy;

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

        RiskLevel riskLevelOnSet = reportService.getRiskLevel(triggersOccurrences, patient);
        Assertions.assertSame(riskLevelOnSet, RiskLevel.EARLYONSET);
    }

//    @Test
//    public void generateReport() {
//        RiskLevel riskLevelNone = reportService.generateReport(1);
//        Assertions.assertSame(riskLevelNone, RiskLevel.NONE);
//        RiskLevel riskLevelBorderline = reportService.generateReport(51);
//        Assertions.assertSame(riskLevelBorderline, RiskLevel.BORDERLINE);
//        RiskLevel riskLevelDanger = reportService.generateReport(101);
//        Assertions.assertSame(riskLevelDanger, RiskLevel.INDANGER);
//        RiskLevel riskLevelOnSet = reportService.generateReport(151);
//        Assertions.assertSame(riskLevelOnSet, RiskLevel.EARLYONSET);
//    }

    @Test
    public void generateReport() {
        Patient patient = patientsProxy.getPatientById(1).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + 1));
        List<Note> notes = notesProxy.getAllNotes(1);

        String observations = notes.stream()
                .map(Note::getObservations)
                .reduce((a, b) -> a.concat(" ").concat(b))
                .orElse("");

        int triggersOccurrences = reportService.countTriggersOccurrences(observations);

        RiskLevel riskLevel = reportService.getRiskLevel(triggersOccurrences, patient);
    }

}

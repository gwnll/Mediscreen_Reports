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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ReportServiceIntegrationTests {

    @Autowired
    ReportService reportService;

    @MockBean
    NotesProxy notesProxy;

    @MockBean
    PatientsProxy patientsProxy;

    @Test
    public void generateReportNone() {
        Patient TestNone = new Patient();
        TestNone.setBirthdate("31-12-1966");
        TestNone.setGender(Gender.FEMALE);
        List<Note> notes = new ArrayList<Note>();
        notes.add(new Note("Patient states that they are 'feeling terrific' Weight at or below recommended level"));

        Mockito.when(patientsProxy.getPatientById(Mockito.any())).thenReturn(Optional.of(TestNone));
        Mockito.when(notesProxy.getAllNotes(Mockito.any())).thenReturn(notes);

        RiskLevel riskLevel = reportService.generateReport(patientsProxy.getPatientById(0).orElseThrow());
        Assertions.assertSame(RiskLevel.NONE, riskLevel);
    }

    @Test
    public void generateReportBorderline() {
        Patient testBorderline = new Patient();
        testBorderline.setBirthdate("24-06-1945");
        testBorderline.setGender(Gender.MALE);
        List<Note> notes = new ArrayList<Note>();
        notes.add(new Note("Patient states that they are feeling a great deal of stress at work Patient also complains that their hearing seems Abnormal as of late"));
        notes.add(new Note("Patient states that they have had a Reaction to medication within last 3 months Patient also complains that their hearing continues to be problematic"));

        Mockito.when(patientsProxy.getPatientById(Mockito.any())).thenReturn(Optional.of(testBorderline));
        Mockito.when(notesProxy.getAllNotes(Mockito.any())).thenReturn(notes);

        RiskLevel riskLevel = reportService.generateReport(patientsProxy.getPatientById(1).orElseThrow());
        Assertions.assertSame(RiskLevel.BORDERLINE, riskLevel);
    }

    @Test
    public void generateReportInDanger() {
        Patient testInDanger = new Patient();
        testInDanger.setBirthdate("18-06-2004");
        testInDanger.setGender(Gender.MALE);
        List<Note> notes = new ArrayList<Note>();
        notes.add(new Note("Patient states that they are short term Smoker"));
        notes.add(new Note("Patient states that they quit within last year Patient also complains that of Abnormal breathing spells Lab reports Cholesterol LDL high"));

        Mockito.when(patientsProxy.getPatientById(Mockito.any())).thenReturn(Optional.of(testInDanger));
        Mockito.when(notesProxy.getAllNotes(Mockito.any())).thenReturn(notes);

        RiskLevel riskLevel = reportService.generateReport(patientsProxy.getPatientById(1).orElseThrow());
        Assertions.assertSame(RiskLevel.INDANGER, riskLevel);
    }

    @Test
    public void generateReportEarlyOnset() {
        Patient testEarlyOnset = new Patient();
        testEarlyOnset.setBirthdate("28-06-2002");
        testEarlyOnset.setGender(Gender.FEMALE);
        List<Note> notes = new ArrayList<Note>();
        notes.add(new Note("Patient states that walking up stairs has become difficult Patient also complains that they are having shortness of breath Lab results indicate Antibodies present elevated Reaction to medication"));
        notes.add(new Note("Patient states that they are experiencing back pain when seated for a long time"));
        notes.add(new Note("Patient states that they are a short term Smoker Hemoglobin A1C above recommended level"));
        notes.add(new Note("Patient states that Body Height, Body Weight, Cholesterol, Dizziness and Reaction"));

        Mockito.when(patientsProxy.getPatientById(Mockito.any())).thenReturn(Optional.of(testEarlyOnset));
        Mockito.when(notesProxy.getAllNotes(Mockito.any())).thenReturn(notes);

        RiskLevel riskLevel = reportService.generateReport(patientsProxy.getPatientById(1).orElseThrow());
        Assertions.assertSame(RiskLevel.EARLYONSET, riskLevel);
    }
}

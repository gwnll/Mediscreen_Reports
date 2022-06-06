package com.mediscreen.reports.service;

import com.mediscreen.reports.model.*;
import com.mediscreen.reports.proxies.NotesProxy;
import com.mediscreen.reports.proxies.PatientsProxy;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ReportService {

    private final NotesProxy notesProxy;
    private final PatientsProxy patientsProxy;

    public ReportService(NotesProxy notesProxy, PatientsProxy patientsProxy) {
        this.notesProxy = notesProxy;
        this.patientsProxy = patientsProxy;
    }

    public RiskLevel generateReport(int patientId) {
        Patient patient = patientsProxy.getPatientById(patientId).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + patientId));
        List<Note> notes = notesProxy.getAllNotes(patientId);

        String observations = notes.stream()
                .map(Note::getObservations)
                .reduce((a, b) -> a.concat(" ").concat(b))
                .orElse("");

        int triggersOccurrences = countTriggersOccurrences(observations);

        return getRiskLevel(triggersOccurrences, patient);

    }

    private int countTriggersOccurrences(String observations) {
        return Arrays.stream(Trigger.values())
                .map(e -> observations.contains(e.getEn()) || observations.contains(e.getFr()) ? 1 : 0)
                .reduce(Integer::sum)
                .orElse(0);
    }

    private RiskLevel getRiskLevel(int triggersOccurrences, Patient patient) {
        RiskLevel riskLevel = RiskLevel.NONE;
        if ((triggersOccurrences == 2) && (patient.getAge() <= 30)) {
            riskLevel = RiskLevel.BORDERLINE;
        } else if (((triggersOccurrences == 3) && (patient.getAge() <= 30) && (patient.getGender() == Gender.MALE)) ||
                ((triggersOccurrences == 4) && (patient.getAge() <= 30) && (patient.getGender() == Gender.FEMALE)) ||
                ((triggersOccurrences == 6) && (patient.getAge() >= 30))) {
            riskLevel = RiskLevel.INDANGER;
        } else if (((triggersOccurrences == 5) && (patient.getAge() <= 30) && (patient.getGender() == Gender.MALE)) ||
                ((triggersOccurrences == 7) && (patient.getAge() <= 30) && (patient.getGender() == Gender.FEMALE)) ||
                ((triggersOccurrences >= 8) && (patient.getAge() >= 30))) {
            riskLevel = RiskLevel.EARLYONSET;
        }
        return riskLevel;

    }
}

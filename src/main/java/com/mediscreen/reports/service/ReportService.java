package com.mediscreen.reports.service;

import com.mediscreen.reports.model.*;
import com.mediscreen.reports.proxies.NotesProxy;
import com.mediscreen.reports.proxies.PatientsProxy;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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

    public RiskLevel generateReport(Patient patient) {
        List<Note> notes = notesProxy.getAllNotes(patient.getId());

        String observations = notes.stream()
                .map(Note::getObservations)
                .reduce((a, b) -> a.concat(" ").concat(b))
                .orElse("");

        int triggersOccurrences = countTriggersOccurrences(observations);

        return getRiskLevel(triggersOccurrences, patient);

    }

    public int countTriggersOccurrences(String observations) {
        String lowerCase = observations.toLowerCase(Locale.ROOT);
        return Arrays.stream(Trigger.values())
                .map(e -> lowerCase.contains(e.getEn()) || lowerCase.contains(e.getFr()) ? 1 : 0)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public RiskLevel getRiskLevel(int triggersOccurrences, Patient patient) {
        RiskLevel riskLevel = RiskLevel.NONE;
        if (((triggersOccurrences >= 5) && (patient.getAge() <= 30) && (patient.getGender() == Gender.MALE)) ||
                ((triggersOccurrences >= 7) && (patient.getAge() <= 30) && (patient.getGender() == Gender.FEMALE)) ||
                ((triggersOccurrences >= 8) && (patient.getAge() >= 30))) {
            riskLevel = RiskLevel.EARLYONSET;
        } else if (((triggersOccurrences >= 3) && (patient.getAge() <= 30) && (patient.getGender() == Gender.MALE)) ||
                ((triggersOccurrences >= 4) && (patient.getAge() <= 30) && (patient.getGender() == Gender.FEMALE)) ||
                ((triggersOccurrences >= 6) && (patient.getAge() >= 30))) {
            riskLevel = RiskLevel.INDANGER;
        } else if
        ((triggersOccurrences >= 2) && (patient.getAge() >= 30)) {
            riskLevel = RiskLevel.BORDERLINE;
        }
        return riskLevel;

    }
}

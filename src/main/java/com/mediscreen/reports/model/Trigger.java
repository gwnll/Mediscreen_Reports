package com.mediscreen.reports.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Trigger {

    HEMOGLOBINE("Hémoglobine A1C", "Hemoglobine A1C"),
    MICROALBUMINE("Microalbumine", "Microalbumine"),
    TAILLE("Taille", "Height"),
    POIDS("Poids", "Weight"),
    FUMEUR("Fumeur", "Smoker"),
    ANORMAL("Anormal", "Abnormal"),
    CHOLESTÉROL("Cholestérol", "Cholesterol"),
    VERTIGE("Vertige", "Vizziness");

    private final String fr;

    private final String en;

    Trigger(String fr, String en) {
        this.fr = fr;
        this.en = en;
    }

    public String getEn() {
        return en;
    }

    public String getFr() {
        return fr;
    }
}

package com.mediscreen.reports.model;

public enum Trigger {

    HEMOGLOBINE("hémoglobine A1C", "hemoglobine A1C"),
    MICROALBUMINE("microalbumine", "microalbumine"),
    TAILLE("taille", "height"),
    POIDS("poids", "weight"),
    FUMEUR("fumeur", "smoker"),
    ANORMAL("anormal", "abnormal"),
    CHOLESTÉROL("cholestérol", "cholesterol"),
    VERTIGE("vertige", "dizziness"),
    RECHUTE("rechute", "relapse"),
    REACTION("réaction", "reaction"),
    ANTICORPS("anticorps", "antibodies");

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

package com.mediscreen.reports.model;

public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private final String displayValue;

    private Gender(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}

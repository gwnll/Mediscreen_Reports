package com.mediscreen.reports.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Data
public class Patient {

    private int id;

    @NotNull(message = "lastName cannot be null")
    private String lastName;

    @NotNull(message = "firstName cannot be null")
    private String firstName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String birthdate;

    private Gender gender;

    private String address;

    private String phone;

    public int getAge() {
        LocalDate birthday = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate today = LocalDate.now();
        return Period.between(birthday, today).getYears();

    }

}

package com.mediscreen.reports.proxies;

import com.mediscreen.reports.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "patients", url = "patients:8081")
public interface PatientsProxy {

    @GetMapping("patient/getPatientById/{id}")
    Optional<Patient> getPatientById(@PathVariable Integer id);

    @PostMapping("patient/updatePatient")
    void updatePatient(@RequestBody Patient patient);

}

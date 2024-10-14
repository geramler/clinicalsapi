package com.example.patientclinicals.clinicalsapi.controllers;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.patientclinicals.clinicalsapi.dto.ClinicalDataRequest;
import com.example.patientclinicals.clinicalsapi.entity.ClinicalData;
import com.example.patientclinicals.clinicalsapi.entity.Patient;
import com.example.patientclinicals.clinicalsapi.repos.ClinicalDataRepository;
import com.example.patientclinicals.clinicalsapi.repos.PatientRepository;

@RestController
@RequestMapping("/api/clinicaldata")
public class ClinicalDataController {

    @Autowired
    private ClinicalDataRepository clinicalDataRepository;
    @Autowired
    private PatientRepository patientRepository;

    // Get all clinical data
    @GetMapping
    public List<ClinicalData> getAllClinicalData() {
        return clinicalDataRepository.findAll();
    }

    // Get clinical data by ID
    @GetMapping("/{id}")
    public Optional<ClinicalData> getClinicalDataById(@PathVariable Integer id) {
        return clinicalDataRepository.findById(id);
    }

    // Update existing clinical data
    @PutMapping("/{id}")
    public ClinicalData updateClinicalData(@PathVariable Integer id, @RequestBody ClinicalData clinicalDataDetails) {
        return clinicalDataRepository.findById(id).map(clinicalData -> {
            clinicalData.setComponentName(clinicalDataDetails.getComponentName());
            clinicalData.setComponentValue(clinicalDataDetails.getComponentValue());
            clinicalData.setMeasuredDateTime(Timestamp.valueOf(LocalDateTime.now()));
            return clinicalDataRepository.save(clinicalData);
        }).orElseGet(() -> {
            return clinicalDataRepository.save(clinicalDataDetails);
        });
    }

    // Delete clinical data by ID
    @DeleteMapping("/{id}")
    public void deleteClinicalData(@PathVariable Integer id) {
        clinicalDataRepository.deleteById(id);
    }

    // method that receives patient id, clinical data and saves it to the database
    @PostMapping("/clinicals")
    public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest clinicalDataRequest) {
        Patient patient = patientRepository.findById(clinicalDataRequest.getPatientId()).get();
        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setComponentName(clinicalDataRequest.getComponentName());
        clinicalData.setComponentValue(clinicalDataRequest.getComponentValue());
        clinicalData.setPatient(patient);
        return clinicalDataRepository.save(clinicalData);
    }
    
}
package com.example.patientclinicals.clinicalsapi.clinicalsapi.controllers;

import com.example.patientclinicals.clinicalsapi.clinicalsapi.entity.ClinicalData;
import com.example.patientclinicals.clinicalsapi.clinicalsapi.repos.ClinicalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clinicaldata")
public class ClinicalDataController {

    @Autowired
    private ClinicalDataRepository clinicalDataRepository;

    // Create new clinical data
    @PostMapping
    public ClinicalData createClinicalData(@RequestBody ClinicalData clinicalData) {
        return clinicalDataRepository.save(clinicalData);
    }

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
            clinicalData.setMeasuredDateTime(clinicalDataDetails.getMeasuredDateTime());
            // Update other fields as necessary
            return clinicalDataRepository.save(clinicalData);
        }).orElseGet(() -> {
            // clinicalDataDetails.setId(id);
            return clinicalDataRepository.save(clinicalDataDetails);
        });
    }

    // Delete clinical data by ID
    @DeleteMapping("/{id}")
    public void deleteClinicalData(@PathVariable Integer id) {
        clinicalDataRepository.deleteById(id);
    }
}
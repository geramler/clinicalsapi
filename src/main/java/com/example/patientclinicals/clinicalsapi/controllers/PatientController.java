package com.example.patientclinicals.clinicalsapi.controllers;

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

import com.example.patientclinicals.clinicalsapi.entity.Patient;
import com.example.patientclinicals.clinicalsapi.repos.PatientRepository;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    // Create a new patient
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    // Get all patients
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Get a patient by ID
    @GetMapping("/{id}")
    public Optional<Patient> getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id);
    }

    // Update an existing patient
    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        return patientRepository.findById(id).map(patient -> {
            patient.setFirstName(patientDetails.getFirstName());
            patient.setLastName(patientDetails.getLastName());
            patient.setAge(patientDetails.getAge());
            // Update other fields as necessary
            return patientRepository.save(patient);
        }).orElseGet(() -> {
            patientDetails.setId(id);
            return patientRepository.save(patientDetails);
        });
    }

    // Delete a patient by ID
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientRepository.deleteById(id);
    }
    
}
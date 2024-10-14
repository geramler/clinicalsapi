package com.example.patientclinicals.clinicalsapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.patientclinicals.clinicalsapi.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
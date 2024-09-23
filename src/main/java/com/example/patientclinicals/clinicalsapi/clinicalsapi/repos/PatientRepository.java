package com.example.patientclinicals.clinicalsapi.clinicalsapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.patientclinicals.clinicalsapi.clinicalsapi.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
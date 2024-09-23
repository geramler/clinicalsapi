package com.example.patientclinicals.clinicalsapi.clinicalsapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.patientclinicals.clinicalsapi.clinicalsapi.entity.ClinicalData;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {

}
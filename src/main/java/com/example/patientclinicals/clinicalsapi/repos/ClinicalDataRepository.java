package com.example.patientclinicals.clinicalsapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.patientclinicals.clinicalsapi.entity.ClinicalData;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {

}
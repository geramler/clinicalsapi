package com.example.patientclinicals.clinicalsapi.clinicalsapi.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.patientclinicals.clinicalsapi.entity.Patient;
import com.example.patientclinicals.clinicalsapi.repos.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreatePatient() throws Exception {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setAge(30);

        Mockito.when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(patient);

        mockMvc.perform(post("/api/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.age").value(30));
    }

    @Test
    public void testGetAllPatients() throws Exception {
        Patient patient1 = new Patient();
        patient1.setId(1L);
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        patient1.setAge(30);

        Patient patient2 = new Patient();
        patient2.setId(2L);
        patient2.setFirstName("Jane");
        patient2.setLastName("Doe");
        patient2.setAge(25);

        Mockito.when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));

        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].age").value(30))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Doe"))
                .andExpect(jsonPath("$[1].age").value(25));
    }

    @Test
    public void testGetPatientById() throws Exception {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setAge(30);

        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        mockMvc.perform(get("/api/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.age").value(30));
    }

    @Test
    public void testUpdatePatient() throws Exception {
        Patient existingPatient = new Patient();
        existingPatient.setId(1L);
        existingPatient.setFirstName("John");
        existingPatient.setLastName("Doe");
        existingPatient.setAge(30);

        Patient updatedPatient = new Patient();
        updatedPatient.setId(1L);
        updatedPatient.setFirstName("Johnny");
        updatedPatient.setLastName("Doe");
        updatedPatient.setAge(31);

        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(existingPatient));
        Mockito.when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(updatedPatient);

        mockMvc.perform(put("/api/patients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPatient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Johnny"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.age").value(31));
    }

    @Test
    public void testDeletePatient() throws Exception {
        Mockito.doNothing().when(patientRepository).deleteById(1L);

        mockMvc.perform(delete("/api/patients/1"))
                .andExpect(status().isOk());

        Mockito.verify(patientRepository, Mockito.times(1)).deleteById(1L);
    }
    
}
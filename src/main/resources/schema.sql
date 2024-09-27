-- Create the Patient table
CREATE TABLE IF NOT EXISTS patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    address VARCHAR(255)
);

-- Create the ClinicalData table
CREATE TABLE IF NOT EXISTS clinicaldata (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    component_name VARCHAR(255) NOT NULL,
    component_value VARCHAR(255) NOT NULL,
    measured_date_time TIMESTAMP NOT NULL,
    patient_id BIGINT NOT NULL,
    CONSTRAINT fk_patient
        FOREIGN KEY (patient_id) 
        REFERENCES patient(id)
);
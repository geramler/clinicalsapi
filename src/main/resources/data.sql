-- Insert initial data into the Patient table
INSERT INTO patient (first_name, last_name, age) VALUES
('John', 'Doe', 30),
('Jane', 'Smith', 25),
('Alice', 'Johnson', 40),
('Bob', 'Brown', 50),
('Charlie', 'Davis', 35),
('Diana', 'Miller', 28),
('Eve', 'Wilson', 22),
('Frank', 'Moore', 45),
('Grace', 'Taylor', 33),
('Hank', 'Anderson', 38);

-- Insert initial data into the ClinicalData table
INSERT INTO clinicaldata (component_name, component_value, measured_date_time, patient_id) VALUES
('Blood Pressure', '120/80', '2023-01-01 10:00:00', 1),
('Heart Rate', '72', '2023-01-01 10:05:00', 1),
('Blood Pressure', '130/85', '2023-01-02 10:00:00', 2),
('Heart Rate', '75', '2023-01-02 10:05:00', 2),
('Blood Pressure', '110/70', '2023-01-03 10:00:00', 3),
('Heart Rate', '68', '2023-01-03 10:05:00', 3),
('Blood Pressure', '125/80', '2023-01-04 10:00:00', 4),
('Heart Rate', '70', '2023-01-04 10:05:00', 4),
('Blood Pressure', '115/75', '2023-01-05 10:00:00', 5),
('Heart Rate', '65', '2023-01-05 10:05:00', 5),
('Blood Pressure', '140/90', '2023-01-06 10:00:00', 6),
('Heart Rate', '80', '2023-01-06 10:05:00', 6),
('Blood Pressure', '135/85', '2023-01-07 10:00:00', 7),
('Heart Rate', '78', '2023-01-07 10:05:00', 7),
('Blood Pressure', '120/75', '2023-01-08 10:00:00', 8),
('Heart Rate', '72', '2023-01-08 10:05:00', 8),
('Blood Pressure', '125/78', '2023-01-09 10:00:00', 9),
('Heart Rate', '74', '2023-01-09 10:05:00', 9),
('Blood Pressure', '130/80', '2023-01-10 10:00:00', 10),
('Heart Rate', '76', '2023-01-10 10:05:00', 10);
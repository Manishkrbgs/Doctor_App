package com.doctor.doctor_app.Repository;

import com.doctor.doctor_app.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IPatientRepo extends JpaRepository<Patient, Long> {
    Patient findFirstByPatientEmail(String newEmail);
}

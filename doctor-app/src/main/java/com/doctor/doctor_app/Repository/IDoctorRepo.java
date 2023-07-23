package com.doctor.doctor_app.Repository;

import com.doctor.doctor_app.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IDoctorRepo extends JpaRepository<Doctor, Long> {
}

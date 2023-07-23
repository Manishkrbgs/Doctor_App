package com.doctor.doctor_app.Repository;

import com.doctor.doctor_app.Model.Appointment;
import com.doctor.doctor_app.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IAppointmentRepo extends JpaRepository<Appointment, Long> {
    Appointment findFirstByPatient(Patient patient);
}

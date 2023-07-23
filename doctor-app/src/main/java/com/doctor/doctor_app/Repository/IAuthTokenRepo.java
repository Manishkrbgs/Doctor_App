package com.doctor.doctor_app.Repository;

import com.doctor.doctor_app.Model.AuthenticationToken;
import com.doctor.doctor_app.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IAuthTokenRepo extends JpaRepository<AuthenticationToken, Long> {
    AuthenticationToken findFirstByTokenValue(String authTokenValue);

    AuthenticationToken findFirstByPatient(Patient patient);
}

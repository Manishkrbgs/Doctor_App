package com.doctor.doctor_app.Repository;

import com.doctor.doctor_app.Model.Admins;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IAdminRepo extends JpaRepository<Admins, Long> {
}

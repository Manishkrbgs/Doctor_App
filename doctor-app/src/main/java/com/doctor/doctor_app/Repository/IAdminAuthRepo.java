package com.doctor.doctor_app.Repository;

import com.doctor.doctor_app.Model.AdminAuth;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IAdminAuthRepo extends JpaRepository<AdminAuth, Long> {
}

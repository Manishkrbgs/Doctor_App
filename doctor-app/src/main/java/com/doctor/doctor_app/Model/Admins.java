package com.doctor.doctor_app.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Admins {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column
    private String adminName;

    @Column
    private String adminEmail;

    @Column
    private String adminPassword;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admins_id", unique = true)
    private AdminAuth adminAuth;


}

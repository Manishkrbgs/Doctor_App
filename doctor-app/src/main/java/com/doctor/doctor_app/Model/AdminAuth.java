package com.doctor.doctor_app.Model;

import com.doctor.doctor_app.Model.Admins;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
public class AdminAuth {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authID;

    @Column
    private String authTokenAdmin;

    @OneToOne(mappedBy = "adminAuth", fetch = FetchType.LAZY)
    private Admins admins;

    public AdminAuth(Admins admins)
    {
        this.admins = admins;
        this.authTokenAdmin = UUID.randomUUID().toString();

    }

}

package com.doctor.doctor_app.Model;

import com.doctor.doctor_app.Model.Appointment;
import com.doctor.doctor_app.Model.AuthenticationToken;
import com.doctor.doctor_app.Model.Doctor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Patient {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @Column
    private String patientName;

    @Column
    private String patientEmail;

    @Column
    private String patientPassword;

    @OneToOne(
            mappedBy = "patient",
            fetch = FetchType.LAZY
    )
    private AuthenticationToken authenticationToken;

    @OneToOne(mappedBy = "patient", fetch = FetchType.LAZY)
    private Appointment appointment;



}

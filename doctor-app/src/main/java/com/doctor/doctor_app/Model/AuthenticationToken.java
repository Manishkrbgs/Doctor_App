package com.doctor.doctor_app.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class AuthenticationToken {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    @Column
    private String tokenValue;

    @Column
    private LocalDateTime tokenCreationDateTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authentication_token_id", unique = true)
     Patient patient;
    public AuthenticationToken(Patient patient)
    {
        this.patient = patient;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationDateTime = LocalDateTime.now();
    }

}

package com.doctor.doctor_app.Model.Dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminSignInInput {

    @Pattern(regexp = "^.+@(?i)hospadmin\\.com$")
    private String email;
    private String password;
}
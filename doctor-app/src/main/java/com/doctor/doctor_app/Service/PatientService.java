package com.doctor.doctor_app.Service;


import com.doctor.doctor_app.Model.Appointment;
import com.doctor.doctor_app.Model.AuthenticationToken;
import com.doctor.doctor_app.Model.Dto.SignInInput;
import com.doctor.doctor_app.Model.Dto.SignUpOutput;
import com.doctor.doctor_app.Model.Patient;
import com.doctor.doctor_app.Repository.IAuthTokenRepo;
import com.doctor.doctor_app.Repository.IDoctorRepo;
import com.doctor.doctor_app.Repository.IPatientRepo;

import com.doctor.doctor_app.Service.emailUtility.EmailHandler;
import com.doctor.doctor_app.Service.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {


    @Autowired
    IPatientRepo patientRepo;

    @Autowired
    IDoctorRepo doctorRepo;

    @Autowired
    IAuthTokenRepo authTokenRepo;

    @Autowired
    AppointmentService appointmentService;

    public SignUpOutput signUpPatient(Patient patient) {

        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = patient.getPatientEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this patient email already exists ??
        Patient existingPatient = patientRepo.findFirstByPatientEmail(newEmail);

        if(existingPatient != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }


        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(patient.getPatientPassword());

            //saveAppointment the patient with the new encrypted password

            patient.setPatientPassword(encryptedPassword);
            patientRepo.save(patient);

            return new SignUpOutput(signUpStatus, "Patient registered successfully!!!");
        }
        catch(Exception e)
        {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }


    public String signInPatient(SignInInput signInInput) {


        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if(signInEmail == null)
        {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;


        }


        Patient existingPatient = patientRepo.findFirstByPatientEmail(signInEmail);

        if(existingPatient == null)
        {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }


        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingPatient.getPatientPassword().equals(encryptedPassword))
            {

                AuthenticationToken authToken  = new AuthenticationToken(existingPatient);
                authTokenRepo.save(authToken);

                EmailHandler.sendEmail(signInEmail,"email testing",authToken.getTokenValue());
                return "Token sent to your email";
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e)
        {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }

    }


    public boolean scheduleAppointment(Appointment appointment) {
        //id of doctor
        Long doctorId = appointment.getDoctor().getDoctorId();
        boolean isDoctorValid = doctorRepo.existsById(doctorId);

        //id of patient
        Long patientId = appointment.getPatient().getPatientId();
        boolean isPatientValid = patientRepo.existsById(patientId);

        if(isDoctorValid && isPatientValid)
        {
            appointmentService.saveAppointment(appointment);
            return true;
        }
        else {
            return false;
        }
    }

    public void cancelAppointment(String email) {

        //email -> Patient
        Patient patient = patientRepo.findFirstByPatientEmail(email);

        Appointment appointment = appointmentService.getAppointmentForPatient(patient);

        appointmentService.cancelAppointment(appointment);



    }

    public String sigOutPatient(String email) {

        Patient patient = patientRepo.findFirstByPatientEmail(email);
        authTokenRepo.delete(authTokenRepo.findFirstByPatient(patient));
        return "Patient Signed out successfully";
    }
}

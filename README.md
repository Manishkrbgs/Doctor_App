# Appointment Booking App


## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Controllers](#controllers)
  - [AdminController](#admincontroller)
  - [DoctorController](#doctorcontroller)
  - [PatientController](#patientcontroller)
- [Models](#models)
  - [AdminAuth](#adminauth)
  - [Admins](#admins)
  - [Appointment](#appointment)
  - [AuthenticationToken](#authenticationtoken)
  - [Doctor](#doctor)
  - [Patient](#patient)

## Introduction

The Appointment Booking App is a web-based application designed to help patients book appointments with doctors. It allows patients to sign up, sign in, schedule appointments, and manage their appointments.

## Features

- Sign up as a patient.
- Sign in with valid credentials.
- Schedule an appointment with a doctor.
- View all appointments for a patient.
- View all doctors and their available appointment slots.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Mysql database

## Getting Started

To run the Appointment Booking App locally, follow these steps:

1. Clone the repository
2. Navigate to the project directory: `cd appointment-booking-app`
3. (Optional) If you are using an IDE, import the project as a Maven project.
4. Build the application: `mvn clean install`
5. Run the application: `mvn spring-boot:run`
6. The application will start, and you can access it at `http://localhost:8080`

## Configuration

The application configuration can be found in the `application.properties` file. You may need to modify the database connection settings, server port, or any other properties based on your requirements.

## Controllers

### AdminController

The `AdminController` handles endpoints related to administrative tasks. It includes the following methods:

- `getAllAppointments`: Handles a GET request to fetch all appointments. It returns a list of `Appointment` objects.

### DoctorController

The `DoctorController` handles endpoints related to doctors. It includes the following methods:

- `addDoctor`: Handles a POST request to add a new doctor to the system.
- `getAllDoctors`: Handles a GET request to fetch all doctors. It returns a list of `Doctor` objects.

### PatientController

The `PatientController` handles endpoints related to patients. It includes the following methods:

- `signUpPatient`: Handles a POST request to sign up a new patient. It returns a `SignUpOutput` object.
- `signInPatient`: Handles a POST request to sign in a patient with valid credentials. It returns a string indicating the sign-in status.
- `signOutPatient`: Handles a DELETE request to sign out a patient. It returns a string indicating the sign-out status.
- `getAllPatients`: Handles a GET request to fetch all patients. It returns a list of `Patient` objects.
- `scheduleAppointment`: Handles a POST request to schedule an appointment for a patient. It returns a string indicating the scheduling status.
- `cancelAppointment`: Handles a DELETE request to cancel an appointment for a patient. It returns a string indicating the cancellation status.

## Models

### AdminAuth

The `AdminAuth` model represents the authentication information for administrators. It includes the following attributes:

- `authID` (Long): Unique identifier for the authentication information.
- `authTokenAdmin` (String): Authentication token for administrators.
- `admins` (Admins): One-to-one relationship with the `Admins` entity, representing the administrator associated with this authentication.

### Admins

The `Admins` model represents the administrators in the system. It includes the following attributes:

- `adminId` (Long): Unique identifier for the administrator.
- `adminName` (String): Name of the administrator.
- `adminEmail` (String): Email of the administrator.
- `adminPassword` (String): Password of the administrator.
- `adminAuth` (AdminAuth): One-to-one relationship with the `AdminAuth` entity, representing the authentication information for this administrator.

### Appointment

The `Appointment` model represents a scheduled appointment between a patient and a doctor. It includes the following attributes:

- `appointmentId` (Long): Unique identifier for the appointment.
- `appointmentDesc` (String): Description or reason for the appointment.
- `appointmentScheduleTime` (LocalDateTime): Scheduled time for the appointment.
- `appointmentCreationTime` (LocalDateTime): Time when the appointment was created.
- `patient` (Patient): Many-to-one relationship with the `Patient` entity, representing the patient associated with this appointment.
- `doctor` (Doctor): Many-to-one relationship with the `Doctor` entity, representing the doctor associated with this appointment.

### AuthenticationToken

The `AuthenticationToken` model represents the authentication tokens for patients. It includes the following attributes:

- `tokenId` (Long): Unique identifier for the authentication token.
- `tokenValue` (String): Authentication token value.
- `tokenCreationDateTime` (LocalDateTime): Time when the token was created.
- `patient` (Patient): One-to-one relationship with the `Patient` entity, representing the patient associated with this token.

### Doctor

The `Doctor` model represents the doctors in the system. It includes the following attributes:

- `doctorId` (Long): Unique identifier for the doctor.
- `doctorName` (String): Name of the doctor.
- `doctorContactNumber` (String): Contact number of the doctor.
- `doctorConsultationFee` (Double): Consultation fee charged by the doctor.
- `appointments` (List<Appointment>): One-to-many relationship with the `Appointment` entity, representing the appointments associated with this doctor.

### Patient

The `Patient` model represents the patients in the system. It includes the following attributes:

- `patientId` (Long): Unique identifier for the patient.
- `patientName` (String): Name of the patient.
- `patientEmail` (String): Email of the patient.
- `patientPassword` (String): Password of the patient.
- `authenticationToken` (AuthenticationToken): One-to-one relationship with the `AuthenticationToken` entity, representing the authentication token for this patient.
- `appointment` (Appointment): One-to-one relationship with the `Appointment` entity, representing the appointment associated with this patient.



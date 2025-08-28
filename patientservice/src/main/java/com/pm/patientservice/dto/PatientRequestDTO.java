package com.pm.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PatientRequestDTO {
    //add all the properties we expect to receive
    //validation is one more reason to use DTO
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "name cannot exceed 100 char")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "address cannot be empty")
    private String address;

    @NotBlank(message = "date of birth is required")
    private String dateOfBirth;

    @NotNull(message = "registered date is required")
    private String registeredDate;


    public @NotBlank(message = "Name is required") @Size(max = 100, message = "name cannot exceed 100 char") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") @Size(max = 100, message = "name cannot exceed 100 char") String name) {
        this.name = name;
    }

    public @NotBlank(message = "address cannot be empty") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "address cannot be empty") String address) {
        this.address = address;
    }

    public @NotBlank(message = "Email cannot be empty") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email cannot be empty") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public @NotBlank(message = "date of birth is required") String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotBlank(message = "date of birth is required") String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @NotNull(message = "registered date is required") String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(@NotNull(message = "registered date is required") String registeredDate) {
        this.registeredDate = registeredDate;
    }
}

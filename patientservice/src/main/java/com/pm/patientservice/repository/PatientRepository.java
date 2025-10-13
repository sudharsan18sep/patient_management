package com.pm.patientservice.repository;

import com.pm.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


//Interfaces for database operations
//Gives you ready-made methods like findAll(), save(), deleteById()
//Handles data persistence — talking directly to the database so the rest of your app
// doesn’t have to know SQL details.
@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    boolean existsByEmail(String email);
    //update email validation
    boolean existsByEmailAndIdNot(String email, UUID id);


}

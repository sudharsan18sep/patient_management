package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//Classes with business logic
//Calls the repository layer under the hood
//Acts as the brain of your app — decides how data is processed before sending it to/from the repository.

@Service
public class PatientService {

//final here ensures that your PatientService always uses the same PatientRepository
// instance injected at construction and prevents accidental reassignment
private final PatientRepository patientRepository;

//DInjection through constructor
public PatientService(PatientRepository patientRepository){
    this.patientRepository = patientRepository;
}

//return all the patients from the DB
 public List<PatientResponseDTO> getpatients(){
    List<Patient> patients = patientRepository.findAll();
    //convert patient entity object into patient response DTO object

//.stream() turns that list into a Stream — a sequence of elements that you can process
     //map() transforms each element in the stream into something else
     //Take the original list of patients.
     //Convert each patient into a DTO using the mapper.
     //Gather all the DTOs into a new list
     List<PatientResponseDTO> patientResponseDTOs = patients.stream().map(
             patient -> PatientMapper.toDTO(patient)).toList();

    return patientResponseDTOs;
    //simpler version
    //return patientRepository.findAll().stream().map(PatientMapper::toDTO).toList();

 }

 //receives DTO from controller and converts it into a domain model
 public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
    //.save() requires an entity model object
    Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));

    return PatientMapper.toDTO(newPatient);
 }

}

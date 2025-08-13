package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

//mapper class to convert entity to DTO
//mapper is a stateless class doens't store data no private property
//Stateless classes are predictable — no hidden side effects.
//They are naturally thread-safe.
//They don’t need Spring’s lifecycle management (you can make methods static and just call them).
//They’re perfect for utility tasks like converting objects, formatting strings, or doing math.
public class PatientMapper {
public static PatientResponseDTO toDTO(Patient patient){
    PatientResponseDTO patientDTO = new PatientResponseDTO();
    patientDTO.setId(patient.getId().toString());
    patientDTO.setName(patient.getName().toString());
    patientDTO.setAddress(patient.getAddress().toString());
    patientDTO.setEmail(patient.getEmail().toString());
    patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());

    return patientDTO;
}

}

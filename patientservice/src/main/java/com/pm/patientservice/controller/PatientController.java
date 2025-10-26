package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


//data flow:Client → sends JSON request
//
//Controller → maps request → calls service
//
//Service → applies business logic → uses mapper → calls repository
//
//Repository → handles persistence → executes SQL
//
//Database → stores/retrieves data
//
//Service → maps entity → response DTO
//
//Controller → returns JSON response

//tell spring that it is a rest controller class
@RestController
//also tell spring that it will handle all the "/patients" request
@RequestMapping("/patients")
@Tag(name ="Patient", description ="API for managing Patients")
public class PatientController {

    //for DI
    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //handle all the CRUD reuqest
    //ResponseEntity is a Spring class
    //-Wraps your response body
    //-Lets you set HTTP status codes and headers explicitly.
    //-you can control the entire HTTP response.
    @GetMapping
    @Operation(summary = "Get all patients")
    public ResponseEntity<List<PatientResponseDTO>> getpatients() {
        List<PatientResponseDTO> patients = patientService.getpatients();

        //ResponseEntity.ok() is a shortcut for creating a ResponseEntity with HTTP 200 OK status
        return ResponseEntity.ok().body(patients);

    }

    //ctrl +alt+shift+L to format the code
    //shift *2 to search
    //postmapping for creating
    //@Requestbody will convert  json to patientrequestDTO object
    //@valid will validate with the validation added in patientRequestDTO class
    @PostMapping
    @Operation(summary = " create a new patient")
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);

        return ResponseEntity.ok().body(patientResponseDTO);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Update patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @RequestBody PatientRequestDTO patientRequestDTO)
    {
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);

        return ResponseEntity.ok().body(patientResponseDTO);
    }

@DeleteMapping("/{id}")
@Operation(summary = "delete patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);

        return ResponseEntity.noContent().build();
}
}

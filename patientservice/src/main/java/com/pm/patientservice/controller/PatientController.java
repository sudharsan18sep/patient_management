package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//tell spring that it is a rest controller class
@RestController
//also tell spring that it will handle all the "/patients" request
@RequestMapping("/patients")
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
    public ResponseEntity<List<PatientResponseDTO>> getpatients(){
        List<PatientResponseDTO> patients = patientService.getpatients();

        //ResponseEntity.ok() is a shortcut for creating a ResponseEntity with HTTP 200 OK status
        return ResponseEntity.ok().body(patients);

    }



}

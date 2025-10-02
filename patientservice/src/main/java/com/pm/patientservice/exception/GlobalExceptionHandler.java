package com.pm.patientservice.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

//to create a global exception handler
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //This method will run whenever a @Valid annotated request body fails validation in your controller
    //MethodArgumentNotValidException Thrown automatically by Spring when @Valid detects invalid data, An exception thrown when a controller method’s argument is not valid
    //MAVE - If the method argument is not valid, throw an exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex){

        //create a hashmap to store the errors , field : Validation
        Map<String, String> errors = new HashMap<>();
        //Gets a list of field errors (which field failed and why)

        //getBindingResult() → contains all the details about why validation failed
        //.getFieldErrors() extracts a list of all field-level errors (one for each invalid field in your DTO

        //For every invalid field, take its name and its error message, and put them in a map
        //error.getField() → the name of the field that failed validation (e.g., "name", "age"
        //error.getDefaultMessage() → the human-readable validation message (from the annotation, e.g., "Name is required").
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistException( EmailAlreadyExistsException ex){
       log.warn("Email already Exists {}", ex.getMessage());
        Map<String, String>  errors = new HashMap<>();
       errors.put("message", "Email address Already Exists");
       return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePatientNotFoundException( PatientNotFoundException ex){
        log.warn("Patient ID not found {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("Message", "Patient not Found");
        return ResponseEntity.badRequest().body(errors);
    }

}

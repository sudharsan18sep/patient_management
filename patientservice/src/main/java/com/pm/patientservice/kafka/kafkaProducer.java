package com.pm.patientservice.kafka;

import com.pm.patientservice.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

//all the code for kafka producer to send events to kafka
//an event is sent when a patient is created
@Service
public class kafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(kafkaProducer.class);
    //createa a template##
    //how we define the message types
    //so its a string - byte key value pair
    private final KafkaTemplate<String, byte[]>  kafkaTemplate;

    //tell spring to autowire it thorugh DI using a constructor
    public kafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Patient patient) {
        //patient is inupt because we need to know information about the patient
        //create an event that has all the properties
        PatientEvent event = PatientEvent.newBuilder().setPatientId(patient.getId().toString())
                .setName(patient.getName()).setEmail(patient.getEmail()).setEventType("PATIENT_CREATED")
                .build();

        try{
            //takes two argument topic name  and value
            kafkaTemplate.send("patient", event.toByteArray());
        }catch(Exception e){
            log.error("error while sending event: {} ",event);
        }
    }
}

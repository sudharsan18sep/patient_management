package com.pm.analyticsservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient", groupId = "analytics-service")
    public void consumeEvent(byte[] event){
        try {
            //try to create a patient event object from the byte array
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            //perform business logic
            log.info("Received patient event: {}", patientEvent);
        }
        catch (Exception e){
            log.error("errorDeserializing the event {}",e.getMessage());
        }
    }
}

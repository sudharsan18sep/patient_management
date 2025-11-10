package com.pm.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {

    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    //a variable to hold grpc client
    //execution wait until it completes ie a response
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    //constructor to initialize the blockingStub
    //takes input that is environmental variables to tell where the billingservice address is
    public BillingServiceGrpcClient(
            @Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.service.grpc.port:9001}") int serverPort)
    {
        log.info("connecting to billing service at {}:{}", serverAddress, serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build();
        //pass to blockingStub
        blockingStub = BillingServiceGrpc.newBlockingStub(channel);
    }

    //different methods that uses this connection to interact with the server
    //whenever a patient is created this method createBillingAccount is called
    public BillingResponse createBillingAccount(String patientId, String name, String email){
        BillingRequest request = BillingRequest.newBuilder().setPatientId(patientId).setName(name)
                .setEmail(email).build();
    //whenever the protofile changes this throws error
        BillingResponse response = blockingStub.createBillingAccount(request);

        log.info("Received response from billing service via GRPC: {}", response);

        return response;

    }

}

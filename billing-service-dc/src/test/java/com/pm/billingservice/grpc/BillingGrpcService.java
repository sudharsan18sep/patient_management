package com.pm.billingservice.grpc;

//GRPC server
//service to start the grpc server to receive from client

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    //implement generated grpc stub
    //BillingRequest billingRequest: The request object sent by the client (contains billing info).
    //
    //StreamObserver<BillingResponse> responseObserver: Used to send responses (or errors) back to the client.
public void createBillingAccount(BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver){

    log.info("createBillingAccount request received {}",billingRequest.toString());

    BillingResponse Response = BillingResponse.newBuilder().setAccountId("12345")
            .setStatus("Active").build();

    responseObserver.onNext(Response);
    responseObserver.onCompleted();
    //onNext(response): Sends the response message to the client.
    //
    //onCompleted(): Signals that the server has finished sending responses (for unary or one-response RPCs)
}


}

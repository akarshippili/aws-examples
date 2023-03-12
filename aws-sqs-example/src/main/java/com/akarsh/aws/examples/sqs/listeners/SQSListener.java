package com.akarsh.aws.examples.sqs.listeners;

import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SQSListener {

    @SqsListener("sample-sqs-queue")
    public void onMessage(String message) {
        log.info("received message from queue {}", message);
    }

}

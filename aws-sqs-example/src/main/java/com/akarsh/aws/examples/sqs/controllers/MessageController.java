package com.akarsh.aws.examples.sqs.controllers;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MessageController {

    @Autowired
    private QueueMessagingTemplate template;

    @Value("${cloud.aws.end-point.uri}")
    private String endpoint;

    @GetMapping(path = "/send/{message}")
    public void sendMessageTOQueue(@PathVariable  String message){
        log.info("received request to send message: {}", message);
        template.send(endpoint, MessageBuilder.withPayload(message).build());
    }
}

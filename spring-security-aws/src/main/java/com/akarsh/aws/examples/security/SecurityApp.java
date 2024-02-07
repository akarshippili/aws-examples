package com.akarsh.aws.examples.security;

import com.akarsh.aws.examples.security.config.authentication.RobotAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

@SpringBootApplication
public class SecurityApp implements CommandLineRunner {

    @Autowired
    private ApplicationContext ctx;

    public static void main(String[] args) {
        SpringApplication.run(SecurityApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ApplicationEventPublisher applicationEventPublisher = ctx.getBean(ApplicationEventPublisher.class);
        AuthenticationSuccessEvent event = new AuthenticationSuccessEvent(RobotAuthentication.authenticated());

        new Thread(() -> {
            int count = 10;
            while (count > 0){
                applicationEventPublisher.publishEvent(event);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count--;
            }
        }).start();
    }
}
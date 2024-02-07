package com.akarsh.aws.examples.security.config.listeners;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        // Get the authenticated user
        Authentication authentication = event.getAuthentication();
        // Get the username
        String username = authentication.getName();
        // Log the login event
        log.info("User {} successfully logged in.", username);
    }
}
package com.akarsh.aws.examples.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeRequests(
                        authorizeConfig -> {
                            authorizeConfig.antMatchers("/students/*").authenticated();
                            authorizeConfig.anyRequest().permitAll();
                        }
                )
                .formLogin(withDefaults())
                .addFilterBefore(new RobotFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

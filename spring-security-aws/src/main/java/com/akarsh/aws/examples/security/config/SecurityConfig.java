package com.akarsh.aws.examples.security.config;


import com.akarsh.aws.examples.security.config.authentication.RobotAuthenticationProvider;
import com.akarsh.aws.examples.security.config.filter.RobotFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        var authManager = new ProviderManager(new RobotAuthenticationProvider(List.of("beep-boop", "boop-beep")));

        return httpSecurity
                .authorizeRequests(
                        authorizeConfig -> {
                            authorizeConfig.antMatchers("/students/*").authenticated();
                            authorizeConfig.anyRequest().permitAll();
                        }
                )
                .formLogin(withDefaults())
                .addFilterBefore(new RobotFilter(authManager), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

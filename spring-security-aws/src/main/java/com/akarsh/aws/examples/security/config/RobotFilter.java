package com.akarsh.aws.examples.security.config;

import com.akarsh.aws.examples.security.authentication.RobotAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RobotFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String password = request.getHeader("X-Robot-Password");
        if (!"beep-boop".equals(password)) {
            // Not authenticated
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "text/plain;charset=UTF-8");
            response.getWriter().println("you are not robot");
            return;
        }

        SecurityContext newContext = SecurityContextHolder.createEmptyContext();
        newContext.setAuthentication(new RobotAuthentication());
        SecurityContextHolder.setContext(newContext);
        filterChain.doFilter(request, response);
    }
}

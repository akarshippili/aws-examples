package com.akarsh.aws.examples.security.controller;

import com.akarsh.aws.examples.security.dto.StudentDto;
import com.akarsh.aws.examples.security.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.LobMergeStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/students/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(Authentication authentication, @PathVariable Long studentId) {
        log.info("getStudentById Resource accessed by {}", authentication.getName());
        return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);
    }

}

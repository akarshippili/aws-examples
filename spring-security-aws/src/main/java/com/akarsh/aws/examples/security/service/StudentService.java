package com.akarsh.aws.examples.security.service;

import com.akarsh.aws.examples.security.dto.StudentDto;

public interface StudentService {
    StudentDto getStudentById(Long studentId);
}

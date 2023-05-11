package com.akarsh.aws.examples.security.service;

import com.akarsh.aws.examples.security.dao.entity.Student;
import com.akarsh.aws.examples.security.dao.repo.StudentRepository;
import com.akarsh.aws.examples.security.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public StudentDto getStudentById(Long studentId) {
        Optional<Student> optionalStudent = getRepository().findById(studentId);
        return optionalStudent.map(student -> modelMapper.map(student, StudentDto.class)).orElse(null);
    }
}

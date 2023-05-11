package com.akarsh.aws.examples.security.dao.repo;

import com.akarsh.aws.examples.security.dao.entity.Student;
import lombok.Lombok;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}

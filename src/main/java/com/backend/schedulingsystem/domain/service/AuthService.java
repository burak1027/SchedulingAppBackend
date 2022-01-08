package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.entity.Instructor;
import com.backend.schedulingsystem.domain.model.entity.Student;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface AuthService {
    Optional<String> signinStudent(String username, String password);
    Optional<String> signinInstructor(String username, String password);
    Optional<Student> signupStudent(String name,String surname, String email, String password);
    Optional<Instructor> signupInstructor(String name,String surname, String email, String password);


}

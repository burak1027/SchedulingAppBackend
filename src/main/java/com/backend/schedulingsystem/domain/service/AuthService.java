package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.entity.Admin;
import com.backend.schedulingsystem.domain.model.entity.Instructor;
import com.backend.schedulingsystem.domain.model.entity.Student;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface AuthService {
    Optional<String> signinStudent(String username, String password);
    Optional<String> signinInstructor(String username, String password);
    Optional<String> signinAdmin(String username, String password);

    String signupStudent(String name,String surname, String email, String password);
    String signupInstructor(String name,String surname, String email, String password);



}

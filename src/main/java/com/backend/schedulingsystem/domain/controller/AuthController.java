package com.backend.schedulingsystem.domain.controller;

import com.backend.schedulingsystem.domain.model.dtos.StudentDto;
import com.backend.schedulingsystem.domain.model.entity.Instructor;
import com.backend.schedulingsystem.domain.model.entity.Student;
import com.backend.schedulingsystem.domain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @PostMapping("/student-signin")
    @ResponseBody
    public Optional<String> signinStudent(@RequestParam("email")String email, @RequestParam("password") String password) {
        System.out.println("Inside controller");
        return authService.signinStudent(email,password);
    }
    @PostMapping("/instructor-signin")
    @ResponseBody
    public Optional<String> signinInstructor(@RequestParam("email")String email, @RequestParam("password") String password) {
        System.out.println("Inside controller");
        return authService.signinInstructor(email,password);
    }
    @PostMapping("/student-signup")
    public Optional<Student> signup(@RequestBody  StudentDto studentDto){
        return authService.signupStudent(studentDto.getName(),studentDto.getSurname(),studentDto.getEmail(),studentDto.getPassword());
    }
    @PostMapping("/instructor-signup")
    public Optional<Instructor> signupInstructor(@RequestBody  StudentDto studentDto){
        return authService.signupInstructor(studentDto.getName(),studentDto.getSurname(),studentDto.getEmail(),studentDto.getPassword());
    }
}

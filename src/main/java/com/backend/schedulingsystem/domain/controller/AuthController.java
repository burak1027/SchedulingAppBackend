package com.backend.schedulingsystem.domain.controller;

import com.backend.schedulingsystem.domain.email.EmailSenderService;
import com.backend.schedulingsystem.domain.model.dtos.InstructorDto;
import com.backend.schedulingsystem.domain.model.dtos.StudentDto;
import com.backend.schedulingsystem.domain.model.entity.Instructor;
import com.backend.schedulingsystem.domain.model.entity.Student;
import com.backend.schedulingsystem.domain.service.AuthService;
import com.backend.schedulingsystem.domain.service.InstructorService;
import com.backend.schedulingsystem.domain.service.StudentService;
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
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    StudentService studentService;
    @Autowired
    InstructorService instructorService;

    @PostMapping("/student-signin")
    @ResponseBody
    public String signinStudent(@RequestParam("email")String email, @RequestParam("password") String password) {
        System.out.println("Inside controller");
        String rtn = authService.signinStudent(email,password).get();
        if(!rtn.isEmpty()){
           // emailSenderService.sendEmail("bburakk.1999@gmail.com","I think I did it :D","java email");
        }
        return rtn ;
    }
    @PostMapping("/instructor-signin")
    @ResponseBody
    public String signinInstructor(@RequestParam("email")String email, @RequestParam("password") String password) {
        System.out.println("Inside controller");
        return authService.signinInstructor(email,password).get();
    }

    @PostMapping("/admin-signin")
    @ResponseBody
    public String signinAdmin(@RequestParam("email")String email, @RequestParam("password") String password) {
        System.out.println("Inside controller");
        return authService.signinAdmin(email,password).get();
    }

    @PostMapping("/student-signup")
    public String signup(@RequestBody  StudentDto studentDto){
       StudentDto student = studentService.getStudentByEmail(studentDto.getEmail());
        if(student==null){
            authService.signupStudent(studentDto.getName(),studentDto.getSurname(),studentDto.getEmail(),studentDto.getPassword());
            return "success";
        }
        else{
            return "user exists";
        }

    }
    @PostMapping("/instructor-signup")
    public String signupInstructor(@RequestBody  InstructorDto instructorDto){
        InstructorDto instructor = instructorService.getInstructorByEmail(instructorDto.getEmail());
        if(instructor==null){
            authService.signupInstructor(instructorDto.getName(),instructorDto.getSurname(),instructorDto.getEmail(),instructorDto.getPassword());
            return "success";
        }else{
            return "user exists";
        }

    }
}

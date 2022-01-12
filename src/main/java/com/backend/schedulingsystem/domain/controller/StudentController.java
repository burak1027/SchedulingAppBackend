package com.backend.schedulingsystem.domain.controller;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.dtos.StudentDto;
import com.backend.schedulingsystem.domain.model.entity.Student;
import com.backend.schedulingsystem.domain.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping
    @ResponseBody
    ResponseEntity<String> saveStudent(@RequestBody StudentDto studentDto){
        studentService.saveStudent(studentDto);
        return ResponseEntity.ok("valid");
    }


    @PutMapping
    @ResponseBody
    ResponseEntity<String> updateStudent(@RequestBody StudentDto studentDto){
        studentService.updateStudent(studentDto);
        return ResponseEntity.ok("valid");
    }


    @DeleteMapping
    @ResponseBody
    ResponseEntity<String> deleteStudent(@RequestBody StudentDto studentDto){
        studentService.deleteStudent(studentDto);
        return ResponseEntity.ok("valid");
    }

    @GetMapping("/id/{value}")
    StudentDto getStudentById(@PathVariable("value") long id ){
        System.out.println("HERE IT IS");
        return studentService.getStudentById(id);
    }
    @GetMapping("/email/{value}")
    StudentDto getStudentByEmail(@PathVariable("value") String email ){
        return studentService.getStudentByEmail(email);
    }
//    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/hello")
    String hello(){
        return "hello";
    }
    @PostMapping("/signin")
    @ResponseBody
    public Optional<String> login(@RequestParam("email")String email, @RequestParam("password") String password) {
        System.out.println("Inside controller");
//        return studentService.signin(email,password).toString();
        return studentService.signin(email,password);
    }
    @GetMapping("/courses")
    List<CourseDto> getCourses(@RequestParam("email")String email){
        return studentService.coursesEnrolledByAStudent(email);
    }


}

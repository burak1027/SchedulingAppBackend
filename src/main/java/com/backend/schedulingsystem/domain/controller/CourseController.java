package com.backend.schedulingsystem.domain.controller;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.dtos.StudentDto;
import com.backend.schedulingsystem.domain.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

//    @PostMapping("/student-signup")
//    public String signup(@RequestBody CourseDto courseDto){
//        System.out.println("Inside controller");
//        return c.signinInstructor(email,password).get();
//    }

}

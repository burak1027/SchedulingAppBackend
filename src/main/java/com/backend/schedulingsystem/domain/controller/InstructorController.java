package com.backend.schedulingsystem.domain.controller;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.dtos.InstructorDto;
import com.backend.schedulingsystem.domain.model.dtos.StudentDto;
import com.backend.schedulingsystem.domain.service.CourseDateService;
import com.backend.schedulingsystem.domain.service.CourseService;
import com.backend.schedulingsystem.domain.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    @Autowired
    InstructorService instructorService;
    @Autowired
    CourseDateService courseDateService;
//    @Autowired
//    CourseService courseService;
    @GetMapping("/all")
    List<InstructorDto> allInstructors() {
        System.out.println("HERE IT IS");
        return instructorService.instructorList();
    }
    @GetMapping("/accept-list")
    List<CourseDto> acceptList(@RequestParam("email") String email){
        return instructorService.coursesToAccept(email);
    }
    @GetMapping("/instructor-course")
    List<CourseDto> givenCourses(@RequestParam("email") String email){
        return instructorService.coursesGivenByInstructor(email);
    }

    @PostMapping("/create-course")
    public String creatCourse(@RequestBody CourseCreateDto courseCreateDto){
        System.out.println("Inside controller");
        return instructorService.createCourse(courseCreateDto.getCourseDto(),courseCreateDto.getEmail());
    }
}

package com.backend.schedulingsystem.domain.controller;

import com.backend.schedulingsystem.domain.service.CourseEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enroll")
public class CourseEnrollmentController {

    @Autowired
    CourseEnrollmentService courseEnrollmentService;

    @PostMapping("/courseAcceptance")
    public void accepTheCourse(@RequestParam("courseId") long courseId,@RequestParam("isAccepted") boolean isAccepted){
        courseEnrollmentService.acceptTheCourse(courseId,isAccepted);
    }
    @PostMapping("/enroll-course")
    public void enrollCourse(@RequestParam("studentEmail") String studentMail,@RequestParam("courseId") long courseId){
        courseEnrollmentService.enrollCourse(studentMail,courseId);
    }

}

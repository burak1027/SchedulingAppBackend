package com.backend.schedulingsystem.domain.controller;

import com.backend.schedulingsystem.domain.service.CourseEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enroll")
public class CourseEnrollmentController {

    @Autowired
    CourseEnrollmentService courseEnrollmentService;

    @PostMapping("/course-acceptance")
    public void accepTheCourse(@RequestParam("courseId") long courseId,@RequestParam("isAccepted") boolean isAccepted){
        courseEnrollmentService.acceptTheCourse(courseId,isAccepted);
    }
    @PostMapping("/enroll-course")
    public void enrollCourse(@RequestParam("studentEmail") String studentMail,@RequestParam("courseId") long courseId){
        courseEnrollmentService.enrollCourseRequest(studentMail,courseId);
    }
    @DeleteMapping("/cancel-course-instructor")
    void cancelCourseInstructor(@RequestParam("instructorEmail")String email, @RequestParam("courseId")long courseId){
        courseEnrollmentService.cancelCourseInstructor(courseId,email);
    }
    @DeleteMapping("/cancel-course-student")
    void cancelCourseStudent(@RequestParam("studentEmail")String email, @RequestParam("courseId")long courseId){
        courseEnrollmentService.cancelCourseStudent(courseId,email);
    }

}

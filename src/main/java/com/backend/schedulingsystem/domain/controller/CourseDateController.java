package com.backend.schedulingsystem.domain.controller;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.service.CourseDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/course-date")
public class CourseDateController {

    @Autowired
    CourseDateService courseDateService;
    @GetMapping("/all")
    List<CourseDto> getStudentById2() {
        System.out.println("HERE IT IS");
        return courseDateService.allTakenCourses();
    }
    @GetMapping("/current-day")
    List<CourseDto> getCoursesInCurrentDay() throws ParseException {
        System.out.println("HERE IT IS");
        return courseDateService.coursesInCurrentdate();
    }
    @GetMapping("/given-date")
    List<CourseDto> getCoursesInADate(@RequestParam("date") String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedCurrentDate = sdf.parse(date);
        System.out.println("HERE IT IS");
        return courseDateService.coursesInADate(convertedCurrentDate);
    }
    @GetMapping("/period")
    List<CourseDto> getCoursesBetweenDates(@RequestParam("date1") String date1,@RequestParam("date2") String date2) throws ParseException {
        System.out.println("HERE IT IS");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = sdf.parse(date1);
        Date convertedDate2 = sdf.parse(date2);
        return courseDateService.coursesBetweenDates(convertedDate, convertedDate2);
    }
}

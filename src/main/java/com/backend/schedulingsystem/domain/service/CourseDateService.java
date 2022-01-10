package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.CoursesTaken;
import com.backend.schedulingsystem.domain.model.entity.Instructor;
import com.backend.schedulingsystem.domain.repository.CourseRepository;
import com.backend.schedulingsystem.domain.repository.CoursesTakenRepository;
import com.backend.schedulingsystem.domain.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class CourseDateService {
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CoursesTakenRepository coursesTakenRepository;

    List<Course> allAvailableCourses(long instId){
        Instructor instructor = instructorRepository.findInstructorById(instId);
        List<Course> courseList = instructor.getCourseList();
        courseList.stream().filter(course -> course.getCoursesTaken()==null);
        return courseList;
    }
    List<Course> allCourses(long instId){
        Instructor instructor = instructorRepository.findInstructorById(instId);
        List<Course> courseList = instructor.getCourseList();
        return courseList;
    }
    List<CoursesTaken> coursesInADate(Date date){
        LocalDate localDate = LocalDate.now();
        return null;
    }
    List<CoursesTaken> coursesInCurrentdate(){
        LocalDate localDate = LocalDate.now();
        return null;

    }
    List<CoursesTaken> corsesInAWeek(){
        return null;
    }
    List<CoursesTaken> coursesBetweenDates(Date date1, Date date2){
        return null;
    }

}
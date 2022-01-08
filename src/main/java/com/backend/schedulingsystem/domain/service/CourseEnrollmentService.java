package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.CoursesTaken;
import com.backend.schedulingsystem.domain.model.entity.Student;
import org.springframework.stereotype.Service;

@Service
public interface CourseEnrollmentService {
    void EnrollCourse(String studentMail,long CourseId);
    void createCourse(Course course);
    void reScheduleCourse(CoursesTaken coursesTaken);
    void cancelCourse(Course course);
}

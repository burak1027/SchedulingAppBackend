package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.CourseTaken;
import org.springframework.stereotype.Service;

@Service
public interface CourseEnrollmentService {
    void enrollCourseRequest(String studentMail,long courseId);
    void enrollCourse(String studentMail,long courseId);

    void createCourse(Course course);
    void reScheduleCourse(CourseTaken coursesTaken);
    void cancelCourseStudent(CourseTaken course, String studentEmail);
}

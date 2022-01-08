package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.CoursesTaken;
import com.backend.schedulingsystem.domain.model.entity.Student;
import com.backend.schedulingsystem.domain.repository.CourseRepository;
import com.backend.schedulingsystem.domain.repository.CoursesTakenRepository;
import com.backend.schedulingsystem.domain.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {

    StudentRepository studentRepository;
    CourseRepository courseRepository;
    CoursesTakenRepository coursesTakenRepository;

    @Autowired
    public CourseEnrollmentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, CoursesTakenRepository coursesTakenRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.coursesTakenRepository = coursesTakenRepository;
    }

    @Override
    public void EnrollCourse(String studentMail, long courseId) {
        Student student = studentRepository.findStudentByEmail(studentMail);
        Course course = courseRepository.findCourseById(courseId);
        CoursesTaken coursesTaken = new CoursesTaken();
        coursesTaken.setCourse(course);
        coursesTaken.setStudent(student);
    }

    @Override
    public void createCourse(Course course) {

    }

    @Override
    public void reScheduleCourse(CoursesTaken coursesTaken) {

    }

    @Override
    public void cancelCourse(Course course) {

    }
}

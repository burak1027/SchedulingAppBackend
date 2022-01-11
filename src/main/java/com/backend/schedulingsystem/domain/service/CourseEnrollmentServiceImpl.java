package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.email.EmailSenderService;
import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.CourseTaken;
import com.backend.schedulingsystem.domain.model.entity.Student;
import com.backend.schedulingsystem.domain.repository.CourseRepository;
import com.backend.schedulingsystem.domain.repository.CoursesTakenRepository;
import com.backend.schedulingsystem.domain.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {

    StudentRepository studentRepository;
    CourseRepository courseRepository;
    CoursesTakenRepository coursesTakenRepository;
    EmailSenderService emailSenderService;

    @Autowired
    public CourseEnrollmentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, CoursesTakenRepository coursesTakenRepository, EmailSenderService emailSenderService) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.coursesTakenRepository = coursesTakenRepository;
        this.emailSenderService = emailSenderService;
    }


    @Transactional
    @Override
    public void enrollCourseRequest(String studentMail, long courseId) {
        Student student = studentRepository.findStudentByEmail(studentMail);
        Course course = courseRepository.findCourseById(courseId);
        CourseTaken coursesTaken = new CourseTaken();
        coursesTaken.setCourse(course);
        coursesTaken.setStudent(student);
        String message = String.format("Your course %s is wanted to be enrolled by %s.",course.getTopic(),studentMail);
//        emailSenderService.sendEmail(course.getInstructor().getEmail(),message,"Course enrollment");

    }
    @Transactional
    @Override
    public void enrollCourse(String studentMail, long courseId) {
        Student student = studentRepository.findStudentByEmail(studentMail);
        Course course = courseRepository.findCourseById(courseId);
        CourseTaken coursesTaken = new CourseTaken();
        coursesTaken.setCourse(course);
        coursesTaken.setStudent(student);
    }

    @Override
    public void createCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void reScheduleCourse(CourseTaken coursesTaken) {

    }

    @Transactional
    @Override
    public void cancelCourseStudent(CourseTaken course, String studentEmail) {
        if(studentEmail.equals(course.getStudent().getEmail())){
            coursesTakenRepository.delete(course);
        }
    }
    @Transactional
    @Override
    public void acceptTheCourse(long courseId, boolean isAccepted){
        Course course = courseRepository.findCourseById(courseId);
        if(isAccepted){
            course.setEnrolled(true);
        }
        else{
            coursesTakenRepository.delete(course.getCoursesTaken());
        }
    }

}

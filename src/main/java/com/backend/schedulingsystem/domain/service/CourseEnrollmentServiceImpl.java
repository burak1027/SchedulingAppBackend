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
        if(coursesTakenRepository.findAllByCourseAndStudent(course,student).isEmpty()){
            coursesTakenRepository.save(coursesTaken);
            String message = String.format("Your course %s is wanted to be enrolled by %s.",course.getTopic(),studentMail);
            emailSenderService.sendEmail(course.getInstructor().getEmail(),message,"Course enrollment");
            //TODO

        }

    }
    @Transactional
    @Override
    public void enrollCourse(String studentMail, long courseId) {
//        Student student = studentRepository.findStudentByEmail(studentMail);
//        Course course = courseRepository.findCourseById(courseId);
//        CourseTaken coursesTaken = new CourseTaken();
//        coursesTaken.setCourse(course);
//        coursesTaken.setStudent(student);
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
    public void cancelCourseStudent(long courseId, String studentEmail) {
        Course course = courseRepository.findCourseById(courseId);
        if(studentEmail.equals(course.getCoursesTaken().getStudent().getEmail())){
            course.setEnrolled(false);
            coursesTakenRepository.delete(course.getCoursesTaken());
        }
    }

    @Override
    public void cancelCourseInstructor(long courseId, String instructorEmail) {
        Course course = courseRepository.findCourseById(courseId);
        if(instructorEmail.equals(course.getInstructor().getEmail())){
            if(course.getCoursesTaken()!=null)
                coursesTakenRepository.delete(course.getCoursesTaken());
            courseRepository.delete(course);
        }

    }


    @Transactional
    @Override
    public void acceptTheCourse(long courseId, boolean isAccepted){
        Course course = courseRepository.findCourseById(courseId);
        String email = course.getCoursesTaken().getStudent().getEmail();
        if(isAccepted){
            course.setEnrolled(true);
            //TODO
            String message= String.format("Your request to enroll for course %s is accepted!",course.getTopic());
            emailSenderService.sendEmail(email,message,"Scheduling Acceptance");
        }
        else{
            coursesTakenRepository.delete(course.getCoursesTaken());
            //TODO
            String message= String.format("Your request to enroll for course %s is not accepted!",course.getTopic());
            emailSenderService.sendEmail(email,message,"Scheduling Acceptance");

        }
    }

}

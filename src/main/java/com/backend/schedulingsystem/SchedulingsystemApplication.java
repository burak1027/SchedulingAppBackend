package com.backend.schedulingsystem;

import com.backend.schedulingsystem.domain.model.entity.Student;
import com.backend.schedulingsystem.domain.model.entity.User;
import com.backend.schedulingsystem.domain.repository.CourseRepository;
import com.backend.schedulingsystem.domain.repository.CoursesTakenRepository;
import com.backend.schedulingsystem.domain.repository.StudentRepository;
import com.backend.schedulingsystem.domain.repository.UserRepository;
import com.backend.schedulingsystem.domain.service.InstructorService;
import com.backend.schedulingsystem.domain.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
public class SchedulingsystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SchedulingsystemApplication.class, args);
    }

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CoursesTakenRepository coursesTakenRepository;
    @Autowired
    InstructorService instructorService;
    @Override
    public void run(String... args) throws Exception {
        instructorService.instructorList().forEach(instructorDto -> {
            instructorDto.getCourseList().forEach(courseDto -> {
                System.out.println(courseDto.getTopic());
            });
        });
//        System.out.println(instructorService.instructorList());



//     studentRepository.findAll().forEach(student -> System.out.println(student.getEmail()));

//
//        Student student = new Student();
//        student.setEmail("asd@gmail.com");
//        student.setActive(true);
//        student.setName("Ali");
//        student.setSurname("Yilmaz");
//        student.setPassword("1234");
//        student.setId(1222);
//
//        entityManager.persist(student);
//        userRepository.save(student);
//        System.out.println(studentService.getStudentById(11111).getEmail());
//        System.out.println(userRepository.findUserById(11111).getEmail());
    }
}

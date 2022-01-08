package com.backend.schedulingsystem;

import com.backend.schedulingsystem.domain.model.entity.Student;
import com.backend.schedulingsystem.domain.model.entity.User;
import com.backend.schedulingsystem.domain.repository.StudentRepository;
import com.backend.schedulingsystem.domain.repository.UserRepository;
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


    @Override
    public void run(String... args) throws Exception {

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

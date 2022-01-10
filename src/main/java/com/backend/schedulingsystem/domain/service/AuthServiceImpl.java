package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.entity.Instructor;
import com.backend.schedulingsystem.domain.model.entity.Student;
import com.backend.schedulingsystem.domain.repository.InstructorRepository;
import com.backend.schedulingsystem.domain.repository.StudentRepository;
import com.backend.schedulingsystem.domain.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
    StudentRepository studentRepository;
    InstructorRepository instructorRepository;
    private JwtUtil jwtProvider;
    @Qualifier("authenticationManagerBean")
    @Autowired
    private AuthenticationManager authenticationManager;
    @Qualifier("authenticationManagerBean2")
    @Autowired
    private AuthenticationManager authenticationManager2;

    @Autowired
    public AuthServiceImpl(StudentRepository studentRepository, InstructorRepository instructorRepository, JwtUtil jwtProvider) {
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
        this.jwtProvider = jwtProvider;
    }

    @Transactional
    @Override
    public Optional<String> signinStudent(String username, String password) {
        LOGGER.info("New user attempting to sign in");
        Optional<String> token = Optional.empty();
        Student user = studentRepository.findStudentByEmail(username);
        if (user!=null) {
            if(user.isActive()){
                try {
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                    token = Optional.of(jwtProvider.createToken(username, "STUDENT"));
                } catch (AuthenticationException e){
                    token =Optional.of("wrong password");

                    LOGGER.info("Log in failed for user {}", username);
                }
            }
            else{
                token =Optional.of("user is not activated");
            }

        }
        else{
            token= Optional.of("user does not exists");
        }
        return token;
    }

    @Override
    public Optional<String> signinInstructor(String username, String password) {
        LOGGER.info("New user attempting to sign in");
        Optional<String> token = Optional.empty();
        Instructor user = instructorRepository.findInstructorByEmail(username);
        if (user!=null) {
            if(user.isActive()){
                try {
                    authenticationManager2.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                    token = Optional.of(jwtProvider.createToken(username, "STUDENT"));
                } catch (AuthenticationException e){
                    LOGGER.info("Log in failed for user {}", username);
                }
            }
            else{
                token =Optional.of("user is not activated");
            }

        }
        else{
            token= Optional.of("user does not exists");
        }
        return token;
    }

    @Override
    public Optional<String> signinAdmin(String username, String password) {
        LOGGER.info("New user attempting to sign in");
        Optional<String> token = Optional.empty();
        Instructor user = instructorRepository.findInstructorByEmail(username);
        if (user!=null) {
            if(user.isActive()){
                try {
                    authenticationManager2.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                    token = Optional.of(jwtProvider.createToken(username, "STUDENT"));
                } catch (AuthenticationException e){
                    LOGGER.info("Log in failed for user {}", username);
                }
            }
            else{
                token =Optional.of("user is not activated");
            }

        }
        else{
            token= Optional.of("user does not exists");
        }
        return token;
    }

    @Override
    public Optional<Student> signupStudent(String name,String surname, String email, String password) {

        LOGGER.info("New student attempting to sign up");
        Optional<Student>  user = Optional.ofNullable((studentRepository.findStudentByEmail(email)));
        System.out.println(user.toString());
        if (!user.isPresent()) {
            studentRepository.save(new Student(name,surname,email,password,false));
             user = Optional.of(studentRepository.findStudentByEmail(email));

        }
        return user;
    }

    @Override
    public Optional<Instructor> signupInstructor(String name,String surname, String email, String password) {
        LOGGER.info("New student attempting to sign up");
        Optional<Instructor>  user = Optional.ofNullable((instructorRepository.findInstructorByEmail(email)));
        if (!user.isPresent()) {
            studentRepository.save(new Student(name,surname,email,password,false));
            user = Optional.of(instructorRepository.findInstructorByEmail(email));

        }
        return user;
    }
}

package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.mappers.CourseMapper;
import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.dtos.StudentDto;
import com.backend.schedulingsystem.domain.model.entity.Instructor;
import com.backend.schedulingsystem.domain.model.entity.Student;
import com.backend.schedulingsystem.domain.repository.StudentRepository;
import com.backend.schedulingsystem.domain.mappers.UserMapper;
import com.backend.schedulingsystem.domain.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private JwtUtil jwtProvider;
    @Qualifier("authenticationManagerBean")
    @Autowired
    private AuthenticationManager authenticationManager;




    @Transactional(readOnly = true)
    @Override
    public StudentDto getStudentById(long id) {
        Student student = studentRepository.findStudentById(id);
        System.out.println("STUDENT is "+student);
        return UserMapper.<StudentDto>entityToDto(student,new StudentDto());
    }
    @Transactional(readOnly = true)
    @Override
    public StudentDto getStudentByEmail(String email) {
        Student student =  studentRepository.findStudentByEmail(email);
        return UserMapper.<StudentDto>entityToDto(student,new StudentDto());
    }

    @Transactional
    @Override
    public void saveStudent(StudentDto studentDto) {
        Student student = UserMapper.<Student>dtoToEntity(studentDto,new Student());
        studentRepository.save(student);
    }

    @Transactional
    @Override
    public void deleteStudent(StudentDto studentDto) {
        Student student = UserMapper.<Student>dtoToEntity(studentDto,new Student());
        studentRepository.delete(student);
    }

    @Transactional
    @Override
    public void updateStudent(StudentDto studentDto) {
        Student student = UserMapper.<Student>dtoToEntity(studentDto,new Student());
        studentRepository.save(student);

    }

    @Transactional
    @Override
    public Optional<String> signin(String username, String password) {
        LOGGER.info("New user attempting to sign in");
        Optional<String> token = Optional.empty();
        Student user = studentRepository.findStudentByEmail(username);
        if (user!=null) {
            if(user.isActive()){
                try {
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
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
    public List<CourseDto> coursesEnrolledByAStudent(String username) {
        Student student = studentRepository.findStudentByEmail(username);
        List<CourseDto> courseDtos = new ArrayList<>();
        student.getCourses().forEach(courseTaken -> {
            if(courseTaken.getCourse().isEnrolled()){
                courseDtos.add(CourseMapper.entityToDto(courseTaken.getCourse()));
            }
        });
        return courseDtos;
    }

    @Override
    public List<CourseDto> getRescheduleRequests(String email) {
        Student student = studentRepository.findStudentByEmail(email);
        List<CourseDto> courseDtos = new ArrayList<>();
        student.getCourses().forEach(course -> {
            if(course.getCourse().getReschedule()!=null){
                courseDtos.add(CourseMapper.entityToDto(course.getCourse()));
            }
        });

        return courseDtos;
    }
}

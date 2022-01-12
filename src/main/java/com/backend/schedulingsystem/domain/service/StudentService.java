package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.dtos.StudentDto;
import com.backend.schedulingsystem.domain.model.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {
    StudentDto getStudentById(long id);
    StudentDto getStudentByEmail(String email);
    void saveStudent(StudentDto studentDto);
    void deleteStudent(StudentDto studentDto);
    void updateStudent(StudentDto studentDto);
    Optional<String> signin(String username, String password);

    List<CourseDto> coursesEnrolledByAStudent(String username);
    List<CourseDto> getRescheduleRequests(String email);






}

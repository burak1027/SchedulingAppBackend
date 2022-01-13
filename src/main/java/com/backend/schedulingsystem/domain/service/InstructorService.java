package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.dtos.InstructorDto;
import com.backend.schedulingsystem.domain.model.entity.Course;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface InstructorService {

    InstructorDto getInstructorById(long id);
    InstructorDto getInstructorByEmail(String email);

    void saveInstructor(InstructorDto instructorDto);
    void deleteInstructor(InstructorDto instructorDto);
    void updateInstructor(InstructorDto instructorDto);
    List<Course> coursesGiven(long id);
    List<CourseDto> coursesGivenByInstructor(String email);
    List<InstructorDto> instructorList() throws ParseException;
    List<CourseDto> coursesToAccept(String email);
    void cancelCourse(String email, long id);
    String createCourse(CourseDto courseDto,String email);
    List<CourseDto> getRescheduleRequests(String email);



}

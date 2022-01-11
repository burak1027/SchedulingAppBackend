package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.dtos.InstructorDto;
import com.backend.schedulingsystem.domain.model.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstructorService {

    InstructorDto getInstructorById(long id);
    InstructorDto getInstructorByEmail(String email);

    void saveInstructor(InstructorDto instructorDto);
    void deleteInstructor(InstructorDto instructorDto);
    void updateInstructor(InstructorDto instructorDto);
    List<Course> coursesGiven(long id);
    List<CourseDto> coursesGivenByInstructor(String email, long courseId);
    List<InstructorDto> instructorList();
    List<CourseDto> coursesToAccept(String email);
    void cancelCourse(String email, long id);

}

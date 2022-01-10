package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.dtos.InstructorDto;
import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.Instructor;
import com.backend.schedulingsystem.domain.repository.InstructorRepository;
import com.backend.schedulingsystem.domain.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    @Transactional(readOnly = true)
    @Override
    public InstructorDto getInstructorById(long id) {
        Instructor instructor = (Instructor) instructorRepository.findInstructorById(id);
        return UserMapper.<InstructorDto>entityToDto(instructor,new InstructorDto());
    }

    @Transactional(readOnly = true)
    @Override
    public InstructorDto getInstructorByEmail(String email) {
        Instructor instructor = (Instructor) instructorRepository.findInstructorByEmail(email);
        return UserMapper.<InstructorDto>entityToDto(instructor, new InstructorDto());
    }

    @Transactional
    @Override
    public void saveInstructor(InstructorDto instructorDto) {
        Instructor instructor = UserMapper.<Instructor>dtoToEntity(instructorDto,new Instructor());
        instructorRepository.save(instructor);
    }

    @Transactional
    @Override
    public void deleteInstructor(InstructorDto instructorDto) {
        Instructor instructor = UserMapper.<Instructor>dtoToEntity(instructorDto,new Instructor());
        instructorRepository.delete(instructor);
    }

    @Transactional
    @Override
    public void updateInstructor(InstructorDto instructorDto) {
        Instructor instructor = UserMapper.<Instructor>dtoToEntity(instructorDto,new Instructor());
        instructorRepository.save(instructor);

    }

    @Override
    public List<Course> coursesGiven(long id) {
        Instructor instructor = (Instructor) instructorRepository.findInstructorById(id);
        List<Course> courseList = instructor.getCourseList();

        return courseList;
    }
}

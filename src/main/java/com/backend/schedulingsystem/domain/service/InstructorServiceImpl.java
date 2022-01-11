package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.mappers.CourseMapper;
import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.dtos.InstructorDto;
import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.Instructor;
import com.backend.schedulingsystem.domain.repository.InstructorRepository;
import com.backend.schedulingsystem.domain.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Transactional
    @Override
    public List<Course> coursesGiven(long id) {
        Instructor instructor = (Instructor) instructorRepository.findInstructorById(id);
        List<Course> courseList = instructor.getCourseList();

        return courseList;
    }
    @Transactional
    @Override
    public List<InstructorDto> instructorList() {
        List<Instructor> instructors = instructorRepository.findAll();
        List<InstructorDto> instructorDtos = new ArrayList<>();
        instructors.forEach(instructor -> {
            List<CourseDto> activeCourses = new ArrayList<>();
            instructor.getCourseList().forEach(course -> {
                if(course.getCoursesTaken()==null){
                    activeCourses.add(CourseMapper.entityToDto(course));
                }
            });
            InstructorDto instructorDto = UserMapper.<InstructorDto>entityToDto(instructor,new InstructorDto());
            instructorDto.setCourseList(activeCourses);
            instructorDtos.add(instructorDto);
        });
        return instructorDtos;
    }

}

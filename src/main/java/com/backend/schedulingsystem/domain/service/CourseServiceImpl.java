package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.mappers.CourseMapper;
import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl  implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public void CreateCourse(CourseDto courseDto,String email) {

    }

    @Override
    public void deleteCourse(long id) {

    }

    @Override
    public CourseDto getCourseById(long id) {
        return CourseMapper.entityToDto(courseRepository.findCourseById(id));
    }

    @Override
    public void deleteCourse(CourseDto courseDto) {
        Course course = CourseMapper.dtoToEntity(courseDto);
        courseRepository.delete(course);
    }
}

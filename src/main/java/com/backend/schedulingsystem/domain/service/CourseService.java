package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    void CreateCourse(CourseDto courseDto,String email);
    void deleteCourse(long id);
}

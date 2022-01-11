package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.CourseTaken;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public interface CourseDateService {
    List<CourseDto> allTakenCourses();
    List<CourseTaken> coursesInADate(Date date);
    List<CourseTaken> coursesInCurrentdate();
    List<CourseTaken> coursesBetweenDates(Date date1, Date date2);
}

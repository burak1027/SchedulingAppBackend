package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.CourseTaken;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
@Service
public interface CourseDateService {
    List<CourseDto> allTakenCourses();
    List<CourseDto> coursesInADate(Date date);
    List<CourseDto> coursesInCurrentdate() throws ParseException;
    List<CourseDto> coursesBetweenDates(Date date1, Date date2);
}

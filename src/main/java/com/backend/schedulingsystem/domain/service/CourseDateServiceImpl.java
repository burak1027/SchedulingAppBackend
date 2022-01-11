package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.mappers.CourseMapper;
import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.CourseTaken;
import com.backend.schedulingsystem.domain.model.entity.Instructor;
import com.backend.schedulingsystem.domain.repository.CourseRepository;
import com.backend.schedulingsystem.domain.repository.CoursesTakenRepository;
import com.backend.schedulingsystem.domain.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseDateServiceImpl implements CourseDateService{
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CoursesTakenRepository coursesTakenRepository;

    List<Course> allAvailableCourses(long instId){
        Instructor instructor = instructorRepository.findInstructorById(instId);
        List<Course> courseList = instructor.getCourseList();
        courseList.stream().filter(course -> course.getCoursesTaken()==null);
        return courseList;
    }
    @Transactional
    @Override
    public List<CourseDto> allTakenCourses(){
        List<Course> courseList = courseRepository.findAll();
        List<CourseDto> courseDtoList = new ArrayList<>();
        courseList.forEach(course -> {
            if(course.getCoursesTaken()!=null){
                CourseDto courseDto = CourseMapper.entityToDto(course);
                courseDto.getCourseTakenDto().getStudent().setPassword("");
                courseDto.getInstructor().setPassword("");
                courseDtoList.add(courseDto);

            }
        });
        return courseDtoList;
    }
    @Transactional
    public List<CourseDto> coursesInADate(Date date){
        List<Course> courseList = courseRepository.findAllByDate(date);
        List<CourseDto> courseDtos = new ArrayList<>();
        courseList.forEach(course -> {
            if(course.getCoursesTaken()!=null){
                CourseDto courseDto = CourseMapper.entityToDto(course);
                courseDto.getCourseTakenDto().getStudent().setPassword("");
                courseDto.getInstructor().setPassword("");
                courseDtos.add(courseDto);
            }
        });
        return courseDtos;
    }
//    public List<CourseTaken> coursesInCurrentdate(){
//        LocalDate localDate = LocalDate.now();
//        return null;
//
//    }
    @Transactional
    @Override
    public List<CourseDto> coursesInCurrentdate() throws ParseException {
        LocalDate localDate = LocalDate.now();
        List<Course> courseList = courseRepository.findAllByDate( new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString()));
        List<CourseDto> courseDtos = new ArrayList<>();
        courseList.forEach(course -> {
            if(course.getCoursesTaken()!=null){
                CourseDto courseDto = CourseMapper.entityToDto(course);
                courseDto.getCourseTakenDto().getStudent().setPassword("");
                courseDto.getInstructor().setPassword("");
                courseDtos.add(courseDto);
            }
        });
        return courseDtos;
    }
    public List<CourseDto> coursesBetweenDates(Date date1, Date date2){
        List<Course> courses = courseRepository.findAllWithinDates(date1,date2);
        List<CourseDto> courseDtos = new ArrayList<>();
        courses.forEach(course -> {
            if(course.getCoursesTaken()!=null){
                CourseDto courseDto = CourseMapper.entityToDto(course);
                courseDto.getCourseTakenDto().getStudent().setPassword("");
                courseDto.getInstructor().setPassword("");
                courseDtos.add(courseDto);            }
        });
        return courseDtos;
    }

}

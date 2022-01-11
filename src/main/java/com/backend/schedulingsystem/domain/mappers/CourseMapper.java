package com.backend.schedulingsystem.domain.mappers;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.entity.Course;

public class CourseMapper {

    public static CourseDto entityToDto(Course course){
        CourseDto courseDto = new CourseDto();
        courseDto.setDate(course.getDate());
        courseDto.setEndTime(course.getEndTime());
        courseDto.setId(course.getId());
        courseDto.setStartTime(course.getStartTime());
        courseDto.setLangLvl(course.getLangLvl());
        courseDto.setTopic(course.getTopic());
        return courseDto;
    }
}

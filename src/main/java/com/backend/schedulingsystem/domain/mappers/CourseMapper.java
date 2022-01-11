package com.backend.schedulingsystem.domain.mappers;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.dtos.InstructorDto;
import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.CourseTaken;

public class CourseMapper {

    public static CourseDto entityToDto(Course course){
        if(course==null)
            return null;
        CourseDto courseDto = new CourseDto();
        courseDto.setDate(course.getDate());
        courseDto.setEndTime(course.getEndTime());
        courseDto.setId(course.getId());
        courseDto.setStartTime(course.getStartTime());
        courseDto.setLangLvl(course.getLangLvl());
        courseDto.setTopic(course.getTopic());
        courseDto.setCourseTakenDto(CourseTakenMapper.entityToDto(course.getCoursesTaken()));
        courseDto.setInstructor(UserMapper.<InstructorDto>entityToDto(course.getInstructor(),new InstructorDto()));
        return courseDto;
    }
}

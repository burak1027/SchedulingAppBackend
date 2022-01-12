package com.backend.schedulingsystem.domain.mappers;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.dtos.InstructorDto;
import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.CourseTaken;
import com.backend.schedulingsystem.domain.model.entity.Instructor;

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
        courseDto.setEnrolled(course.isEnrolled());
        courseDto.setRescheduleDto(RescheduleMapper.entityToDto(course.getReschedule()));
        return courseDto;
    }
    public static Course dtoToEntity (CourseDto courseDto){
        if(courseDto==null)
            return null;
        Course course = new Course();
        course.setDate(courseDto.getDate());
        course.setEndTime(courseDto.getEndTime());
        course.setId(courseDto.getId());
        course.setStartTime(courseDto.getStartTime());
        course.setLangLvl(courseDto.getLangLvl());
        course.setTopic(courseDto.getTopic());
        course.setCoursesTaken(CourseTakenMapper.entityToDto(courseDto.getCourseTakenDto()));
        course.setInstructor(UserMapper.<Instructor>dtoToEntity(courseDto.getInstructor(),new Instructor()));
        course.setEnrolled(course.isEnrolled());
        course.setReschedule(RescheduleMapper.dtoToEntity(courseDto.getRescheduleDto()));
        return course;
    }
}

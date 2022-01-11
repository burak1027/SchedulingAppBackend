package com.backend.schedulingsystem.domain.model.dtos;

import com.backend.schedulingsystem.domain.model.entity.Course;

import java.util.List;

public class InstructorDto extends UserDto{
    List<CourseDto> courseList;

    public List<CourseDto> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseDto> courseList) {
        this.courseList = courseList;
    }
}

package com.backend.schedulingsystem.domain.controller;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;

public class CourseCreateDto {
    CourseDto courseDto;
    String email;

    public CourseDto getCourseDto() {
        return courseDto;
    }

    public void setCourseDto(CourseDto courseDto) {
        this.courseDto = courseDto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

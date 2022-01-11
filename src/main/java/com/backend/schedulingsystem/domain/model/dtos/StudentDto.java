package com.backend.schedulingsystem.domain.model.dtos;

import com.backend.schedulingsystem.domain.model.entity.CourseTaken;

import java.util.List;

public class StudentDto extends UserDto {
    List<CourseTaken> courses;

    public List<CourseTaken> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseTaken> courses) {
        this.courses = courses;
    }
}

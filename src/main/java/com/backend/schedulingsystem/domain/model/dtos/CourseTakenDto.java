package com.backend.schedulingsystem.domain.model.dtos;

import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.Student;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class CourseTakenDto {
    long id;
    CourseDto course;
    StudentDto student;

    public void setId(long id) {
        this.id = id;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }

    public void setStudent(StudentDto student) {
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public CourseDto getCourse() {
        return course;
    }

    public StudentDto getStudent() {
        return student;
    }
}

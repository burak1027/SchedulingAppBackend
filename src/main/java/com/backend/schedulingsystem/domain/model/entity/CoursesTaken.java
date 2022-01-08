package com.backend.schedulingsystem.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "taken_course")

public class CoursesTaken {
    @Id
    @GeneratedValue
    @Column(name = "id")
    long id;
    @OneToOne
    @JoinColumn(name = "course_id",nullable = false)
    Course course;
    @ManyToOne
    @JoinColumn(name = "student_id",nullable = false)
    Student student;


    public void setId(long id) {
        this.id = id;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }
}

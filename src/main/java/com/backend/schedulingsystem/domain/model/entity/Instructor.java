package com.backend.schedulingsystem.domain.model.entity;

import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "instructor")
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class Instructor extends User {
    @OneToMany(mappedBy = "instructor")
    List<Course> courseList;

    public List<Course> getCourseList() {
        return courseList;
    }
}

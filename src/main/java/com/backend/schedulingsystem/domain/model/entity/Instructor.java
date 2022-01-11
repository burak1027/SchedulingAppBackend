package com.backend.schedulingsystem.domain.model.entity;

import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "instructor")
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class Instructor extends User {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor",cascade = CascadeType.ALL)
    List<Course> courseList;

    public List<Course> getCourseList() {
        return courseList;
    }
}

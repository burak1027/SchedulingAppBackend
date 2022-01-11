package com.backend.schedulingsystem.domain.model.entity;

import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@Setter
@PrimaryKeyJoinColumn(name = "id")

public class Student extends User{
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "student",cascade = CascadeType.ALL)
    List<CourseTaken> courses;

    public List<CourseTaken> getCourses() {
        return courses;
    }

    public Student(String name, String surname, String email, String password, boolean b) {
        super(name,surname,email,password,false);
    }

    public Student() {

    }
}

package com.backend.schedulingsystem.domain.model.entity;

import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@Setter
@PrimaryKeyJoinColumn(name = "id")

public class Student extends User{
    @OneToMany(mappedBy = "student")
    List<CoursesTaken> courses;
    public Student(String name, String surname, String email, String password, boolean b) {
        super(name,surname,email,password,false);
    }

    public Student() {

    }
}

package com.backend.schedulingsystem.domain.model.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "id")
    long id;
    @Column(name = "date_of_course")
    Date date;
    @Column(name = "start_time")
    Time startTime;
    @Column(name = "end_time")
    Time endTime;
    @Column(name = "lang_lvl")
    String langLvl;
    @Column(name = "topic")
    String topic;
    @ManyToOne
    @JoinColumn(name = "instructor_id",nullable = false)
    Instructor instructor;
    @OneToOne(mappedBy = "course")
    CoursesTaken coursesTaken;
}

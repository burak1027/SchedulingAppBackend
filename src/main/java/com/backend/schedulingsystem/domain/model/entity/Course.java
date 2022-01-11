package com.backend.schedulingsystem.domain.model.entity;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(name = "date_of_course")
    LocalDate date;
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
    CourseTaken coursesTaken;

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setLangLvl(String langLvl) {
        this.langLvl = langLvl;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setCoursesTaken(CourseTaken coursesTaken) {
        this.coursesTaken = coursesTaken;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public String getLangLvl() {
        return langLvl;
    }

    public String getTopic() {
        return topic;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public CourseTaken getCoursesTaken() {
        return coursesTaken;
    }
}

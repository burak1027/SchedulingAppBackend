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
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_course")
    Date date;
    @Temporal(TemporalType.TIME)
    @Column(name = "start_time")
    Date startTime;
    @Temporal(TemporalType.TIME)
    @Column(name = "end_time")
    Date endTime;
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

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
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

    public Date getDate() {
        return date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
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

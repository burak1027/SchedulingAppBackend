package com.backend.schedulingsystem.domain.model.dtos;

import com.backend.schedulingsystem.domain.model.entity.Instructor;

import java.sql.Time;
import java.util.Date;

public class CourseDto {
    long id;
    Date date;
    Time startTime;
    Time endTime;
    String langLvl;
    String topic;
    Instructor instructor;
    CourseTakenDto courseTakenDto;

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(Date date) {
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

    public void setCourseTakenDto(CourseTakenDto courseTakenDto) {
        this.courseTakenDto = courseTakenDto;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
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

    public CourseTakenDto getCourseTakenDto() {
        return courseTakenDto;
    }
}

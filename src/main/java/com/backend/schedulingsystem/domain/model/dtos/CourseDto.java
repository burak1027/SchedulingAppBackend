package com.backend.schedulingsystem.domain.model.dtos;

import com.backend.schedulingsystem.domain.model.entity.Instructor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class CourseDto {
    long id;
    @JsonFormat
            (pattern = "yyyy-MM-dd", timezone ="Turkey")
    Date date;
    @JsonFormat
            ( pattern = "HH:mm",timezone ="GMT+3")
    Date startTime;
    @JsonFormat
            (pattern = "HH:mm", timezone="GMT+3")
    Date endTime;
    String langLvl;
    String topic;
    InstructorDto instructor;
    CourseTakenDto courseTakenDto;
    RescheduleDto rescheduleDto;
    boolean isEnrolled;

    public boolean isEnrolled() {
        return isEnrolled;
    }

    public void setEnrolled(boolean enrolled) {
        isEnrolled = enrolled;
    }

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

    public void setInstructor(InstructorDto instructor) {
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

    public InstructorDto getInstructor() {
        return instructor;
    }

    public CourseTakenDto getCourseTakenDto() {
        return courseTakenDto;
    }

    public RescheduleDto getRescheduleDto() {
        return rescheduleDto;
    }

    public void setRescheduleDto(RescheduleDto rescheduleDto) {
        this.rescheduleDto = rescheduleDto;
    }
}

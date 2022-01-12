package com.backend.schedulingsystem.domain.model.dtos;

import com.backend.schedulingsystem.domain.model.entity.Course;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

public class RescheduleDto {
    long id;

    @JsonFormat
            (pattern = "yyyy-MM-dd")
    Date date;
    @JsonFormat
            ( pattern = "hh:mm")
    Date startTime;
    @JsonFormat
            (pattern = "hh:mm")
    Date endTime;

    CourseDto requestedCourse;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public CourseDto getRequestedCourse() {
        return requestedCourse;
    }

    public void setRequestedCourse(CourseDto requestedCourse) {
        this.requestedCourse = requestedCourse;
    }
}

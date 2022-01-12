package com.backend.schedulingsystem.domain.model.dtos;

import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

public class RescheduleDto {
    long id;
    @JsonFormat
            (pattern = "yyyy-MM-dd",timezone ="Turkey")
    Date date;
    @JsonFormat
            (pattern = "HH:mm",timezone ="Turkey")
    Date startTime;
    @JsonFormat
            (pattern = "HH:mm",timezone ="Turkey")
    Date endTime;

    CourseDto requestedCourse;

    UserDto userDto;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

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

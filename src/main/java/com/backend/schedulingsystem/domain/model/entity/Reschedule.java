package com.backend.schedulingsystem.domain.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reschedule_requests")
public class Reschedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "new_date_of_course")
    Date date;
    @Temporal(TemporalType.TIME)
    @Column(name = "new_start_time")
    Date startTime;
    @Temporal(TemporalType.TIME)
    @Column(name = "new_end_time")
    Date endTime;

    @OneToOne
    @JoinColumn(name = "course_id")
    Course requestedCourse;

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

    public Course getRequestedCourse() {
        return requestedCourse;
    }

    public void setRequestedCourse(Course requestedCourse) {
        this.requestedCourse = requestedCourse;
    }
}

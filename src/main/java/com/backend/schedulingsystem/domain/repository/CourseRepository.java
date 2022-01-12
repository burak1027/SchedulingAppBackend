package com.backend.schedulingsystem.domain.repository;

import com.backend.schedulingsystem.domain.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findCourseById(long id);
    List<Course> findAllByDate(Date date);

    @Query("select a from Course a where a.date <= :endDate and a.date >= :startDate")
    List<Course> findAllWithinDates(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
    @Query("select a from Course a where a.startTime >= :startTime and a.endTime <= :endTime and  a.date = :currentDate")
    List<Course> findAllWithinHours(
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime,
            @Param("currentDate") Date currentDate);

//    List<Course> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(Time endDate, Time startDate);
}

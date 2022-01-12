package com.backend.schedulingsystem.domain.repository;

import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.Reschedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RescheduleRepository extends JpaRepository<Reschedule,Long> {
    List<Reschedule> findAllByRequestedCourse(Course course);


}

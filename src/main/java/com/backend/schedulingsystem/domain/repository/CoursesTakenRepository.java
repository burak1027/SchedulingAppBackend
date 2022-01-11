package com.backend.schedulingsystem.domain.repository;

import com.backend.schedulingsystem.domain.model.entity.CourseTaken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesTakenRepository extends JpaRepository<CourseTaken,Long> {

}

package com.backend.schedulingsystem.domain.repository;

import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.CourseTaken;
import com.backend.schedulingsystem.domain.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursesTakenRepository extends JpaRepository<CourseTaken,Long> {
    List<CourseTaken> findAllByCourseAndStudent(Course course, Student student);
}

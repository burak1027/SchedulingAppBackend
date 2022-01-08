package com.backend.schedulingsystem.domain.repository;

import com.backend.schedulingsystem.domain.model.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {
    Instructor findInstructorById(long id);
    Instructor findInstructorByEmail(String email);

}

package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.dtos.InstructorDto;
import org.springframework.stereotype.Service;

@Service
public interface InstructorService {

    InstructorDto getInstructorById(long id);
    InstructorDto getInstructorByEmail(String email);

    void saveInstructor(InstructorDto instructorDto);
    void deleteInstructor(InstructorDto instructorDto);
    void updateInstructor(InstructorDto instructorDto);

}

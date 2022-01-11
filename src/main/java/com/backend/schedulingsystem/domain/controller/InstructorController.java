package com.backend.schedulingsystem.domain.controller;

import com.backend.schedulingsystem.domain.model.dtos.InstructorDto;
import com.backend.schedulingsystem.domain.model.dtos.StudentDto;
import com.backend.schedulingsystem.domain.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    @Autowired
    InstructorService instructorService;
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/all")
    List<InstructorDto> getStudentById() {
        System.out.println("HERE IT IS");
        return instructorService.instructorList();
    }
}

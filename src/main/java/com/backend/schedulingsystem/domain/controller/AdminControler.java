package com.backend.schedulingsystem.domain.controller;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.dtos.UserDto;
import com.backend.schedulingsystem.domain.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminControler {
    @Autowired
    AdminService adminService;

    @GetMapping("/inactivated-users")
    List<UserDto> getCoursesInADate(){
        return adminService.notActivatedList();
    }
    @PostMapping("/activate-user")
    void activateUser(@RequestParam("email") String email){
        adminService.activateUser(email);
    }
}

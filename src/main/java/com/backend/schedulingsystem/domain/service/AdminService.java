package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.dtos.UserDto;
import com.backend.schedulingsystem.domain.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<UserDto> notActivatedList();
    void activateUser(String userMail);
}

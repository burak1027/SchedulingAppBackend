package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.mappers.UserMapper;
import com.backend.schedulingsystem.domain.model.dtos.UserDto;
import com.backend.schedulingsystem.domain.model.entity.User;
import com.backend.schedulingsystem.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDto> notActivatedList() {
        List<User> userList = userRepository.inactiveUsers();
        List<UserDto> userDtos = new ArrayList<>();
        userList.forEach(user -> userDtos.add(UserMapper.<UserDto>entityToDto(user,new UserDto())));
        return userDtos;
    }

    @Override
    public void activateUser(String userMail) {
        User user = userRepository.findUserByEmail(userMail);
        user.setActive(true);
        userRepository.save(user);
    }
}

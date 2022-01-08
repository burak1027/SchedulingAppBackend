package com.backend.schedulingsystem.domain.mappers;

import com.backend.schedulingsystem.domain.model.dtos.UserDto;
import com.backend.schedulingsystem.domain.model.entity.User;

public class UserMapper {
//    <usr extends User, dto extends UserDto >
    public UserMapper(){

    }
    public static <T extends UserDto >T entityToDto (User user, T dto){

        if(user==null)
            return null;
        dto.setEmail(user.getEmail());
        dto.setActive(user.isActive());
        dto.setId(user.getId());
        dto.setPassword(user.getPassword());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        return dto;
    }
    public static <T extends User> T dtoToEntity(UserDto userDto, T user){

        if(userDto==null)
            return null;

        user.setEmail(userDto.getEmail());
        user.setActive(userDto.isActive());
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        return user;
    }
}

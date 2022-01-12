package com.backend.schedulingsystem.domain.mappers;

import com.backend.schedulingsystem.domain.model.dtos.CourseTakenDto;
import com.backend.schedulingsystem.domain.model.dtos.StudentDto;
import com.backend.schedulingsystem.domain.model.entity.CourseTaken;

public class CourseTakenMapper {

    public static CourseTakenDto entityToDto(CourseTaken courseTaken){
        if(courseTaken == null)
            return null;
        CourseTakenDto courseTakenDto = new CourseTakenDto();
        courseTakenDto.setId(courseTaken.getId());
        courseTakenDto.setStudent(UserMapper.<StudentDto>entityToDto(courseTaken.getStudent(),new StudentDto()));
        return courseTakenDto;
    }
    public static CourseTaken entityToDto(CourseTakenDto courseTakenDto){
        if(courseTakenDto == null)
            return null;
        CourseTaken courseTaken = new CourseTaken();
        courseTakenDto.setId(courseTaken.getId());
        courseTakenDto.setStudent(UserMapper.<StudentDto>entityToDto(courseTaken.getStudent(),new StudentDto()));
        return courseTaken;
    }
}

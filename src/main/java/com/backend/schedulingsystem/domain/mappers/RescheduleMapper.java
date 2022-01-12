package com.backend.schedulingsystem.domain.mappers;

import com.backend.schedulingsystem.domain.model.dtos.RescheduleDto;
import com.backend.schedulingsystem.domain.model.entity.Reschedule;

public class RescheduleMapper {

    public static RescheduleDto entityToDto(Reschedule reschedule){
        if(reschedule==null)
            return null;
        RescheduleDto rescheduleDto = new RescheduleDto();
        rescheduleDto.setDate(reschedule.getDate());
        rescheduleDto.setEndTime(reschedule.getEndTime());
        rescheduleDto.setStartTime(reschedule.getStartTime());
        rescheduleDto.setDate(reschedule.getDate());
        rescheduleDto.setId(reschedule.getId());
        rescheduleDto.setRequestedCourse(CourseMapper.entityToDto(reschedule.getRequestedCourse()));
        return rescheduleDto;
    }
    public static Reschedule dtoToEntity(RescheduleDto rescheduleDto){
        if(rescheduleDto==null)
            return null;
        Reschedule reschedule = new Reschedule();
        reschedule.setDate(rescheduleDto.getDate());
        reschedule.setEndTime(rescheduleDto.getEndTime());
        reschedule.setStartTime(rescheduleDto.getStartTime());
        reschedule.setDate(rescheduleDto.getDate());
        reschedule.setId(rescheduleDto.getId());
        reschedule.setRequestedCourse(CourseMapper.dtoToEntity(rescheduleDto.getRequestedCourse()));
        return reschedule;
    }

}

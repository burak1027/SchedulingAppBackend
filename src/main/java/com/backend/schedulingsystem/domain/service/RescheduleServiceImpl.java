package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.mappers.RescheduleMapper;
import com.backend.schedulingsystem.domain.model.dtos.RescheduleDto;
import com.backend.schedulingsystem.domain.model.entity.Reschedule;
import com.backend.schedulingsystem.domain.repository.CourseRepository;
import com.backend.schedulingsystem.domain.repository.RescheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RescheduleServiceImpl implements RescheduleService{

    @Autowired
    RescheduleRepository rescheduleRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public String rescheduleRequest(RescheduleDto rescheduleDto) {
        rescheduleRepository.save(RescheduleMapper.dtoToEntity(rescheduleDto));
        return "";
    }

    @Override
    public void deleteReschedule(RescheduleDto rescheduleDto) {
        Reschedule reschedule = RescheduleMapper.dtoToEntity(rescheduleDto);
        rescheduleRepository.delete(reschedule);
    }
}

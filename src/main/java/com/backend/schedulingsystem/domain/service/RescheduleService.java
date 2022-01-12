package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.dtos.RescheduleDto;
import com.backend.schedulingsystem.domain.model.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RescheduleService {

    String rescheduleRequest(RescheduleDto rescheduleDto);
    void deleteReschedule(RescheduleDto rescheduleDto);
    void acceptReschedule(long courseId,boolean isAccepted);
}

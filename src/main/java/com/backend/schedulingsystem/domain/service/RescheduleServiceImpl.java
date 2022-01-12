package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.mappers.RescheduleMapper;
import com.backend.schedulingsystem.domain.model.dtos.RescheduleDto;
import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.Reschedule;
import com.backend.schedulingsystem.domain.repository.CourseRepository;
import com.backend.schedulingsystem.domain.repository.RescheduleRepository;
import com.backend.schedulingsystem.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RescheduleServiceImpl implements RescheduleService{

    @Autowired
    RescheduleRepository rescheduleRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public String rescheduleRequest(RescheduleDto rescheduleDto) {
        long id = rescheduleDto.getRequestedCourse().getId();
        System.out.println("ID IS HERE ID IS "+id);
        rescheduleDto.setRequestedCourse(null);
        Course course = courseRepository.findCourseById(id);
        Reschedule reschedule = RescheduleMapper.dtoToEntity(rescheduleDto);
        reschedule.setRequestedCourse(course);
        rescheduleRepository.save(reschedule);
        return "";
    }

    @Transactional
    @Override
    public void deleteReschedule(RescheduleDto rescheduleDto) {
        long id = rescheduleDto.getId();
        rescheduleDto.setRequestedCourse(null);
        Course course = courseRepository.findCourseById(id);
        Reschedule reschedule = RescheduleMapper.dtoToEntity(rescheduleDto);
        reschedule.setRequestedCourse(course);
        rescheduleRepository.delete(reschedule);
    }

    @Transactional
    @Override
    public void acceptReschedule(long courseId, boolean isAccepted) {
        Course course = courseRepository.findCourseById(courseId);
        if(isAccepted){
            Reschedule reschedule = course.getReschedule();
            course.setDate(reschedule.getDate());
            course.setEndTime(reschedule.getEndTime());
            course.setStartTime(reschedule.getStartTime());

        }
        else{

        }
        rescheduleRepository.delete(course.getReschedule());

    }
}

package com.backend.schedulingsystem.domain.controller;

import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.dtos.RescheduleDto;
import com.backend.schedulingsystem.domain.service.CourseService;
import com.backend.schedulingsystem.domain.service.RescheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/reschedule")
public class RescheduleController {
    @Autowired
    RescheduleService rescheduleService;
    @Autowired
    CourseService courseService;
    @PostMapping("/request")
    String resReq(@RequestParam("time1") String time1,@RequestParam("time2") String time2,@RequestParam("date") String date,@RequestParam("courseId") long courseId) throws ParseException {
        CourseDto courseDto = courseService.getCourseById(courseId);
        if(courseDto.getRescheduleDto()!=null)
            rescheduleService.deleteReschedule(courseDto.getRescheduleDto());
        RescheduleDto rescheduleDto = new RescheduleDto();
        rescheduleDto.setRequestedCourse(courseDto);
        rescheduleDto.setDate( new SimpleDateFormat("yyyy-MM-dd").parse(date));
        rescheduleDto.setStartTime(new SimpleDateFormat("hh:mm").parse(time1));
        rescheduleDto.setEndTime(new SimpleDateFormat("hh:mm").parse(time2));

        return rescheduleService.rescheduleRequest(rescheduleDto);

    }
    @PostMapping("/accept")
    void accept(@RequestParam("courseId")long courseId){
        
    }
}

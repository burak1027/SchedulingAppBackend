package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.email.EmailSenderService;
import com.backend.schedulingsystem.domain.mappers.RescheduleMapper;
import com.backend.schedulingsystem.domain.model.dtos.RescheduleDto;
import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.Reschedule;
import com.backend.schedulingsystem.domain.model.entity.User;
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
    @Autowired
    EmailSenderService emailSenderService;


    @Transactional
    @Override
    public String rescheduleRequest(RescheduleDto rescheduleDto) {
        String email = rescheduleDto.getUserDto().getEmail();
        long id = rescheduleDto.getRequestedCourse().getId();
        Course course = courseRepository.findCourseById(id);

        if(course.getCoursesTaken()==null){
            course.setStartTime(rescheduleDto.getStartTime());
            course.setEndTime(rescheduleDto.getEndTime());
            course.setDate(rescheduleDto.getDate());
            courseRepository.save(course);

            return "Course rescheduled";
        }


        Reschedule reschedule = rescheduleRepository.findRescheduleByRequestedCourse(course);
        User user = userRepository.findUserByEmail(email);
        if(course.getReschedule()!=null){
            if(reschedule.getUser().getEmail().equals(email)){
                rescheduleRepository.delete(course.getReschedule());
            }
            else{
                return "There is a request pending for the course reschedule";
            }

        }


        System.out.println("ID IS HERE ID IS "+id);
        rescheduleDto.setRequestedCourse(null);
        rescheduleDto.setUserDto(null);

        Reschedule reschedule2 = RescheduleMapper.dtoToEntity(rescheduleDto);
        reschedule2.setRequestedCourse(course);
        reschedule2.setUser(user);
        rescheduleRepository.save(reschedule2);


        //TODO
        String receiverEmail ;

        if(email.equals(course.getInstructor().getEmail())){
            receiverEmail= course.getCoursesTaken().getStudent().getEmail();
        }
        else{
            receiverEmail = course.getInstructor().getEmail();
        }
        String message= String.format("There is a request pending for the course %s",course.getTopic());
//        emailSenderService.sendEmail(receiverEmail,message,"Re-Scheduling request");

        return "request sent";
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
        Reschedule reschedule = course.getReschedule();
        String email = reschedule.getUser().getEmail();

        if(isAccepted){
            course.setDate(reschedule.getDate());
            course.setEndTime(reschedule.getEndTime());
            course.setStartTime(reschedule.getStartTime());
            //TODO
            String message= String.format("Your request to reschedule for course %s is accepted!",course.getTopic());
//            emailSenderService.sendEmail(email,message,"Scheduling Acceptance");


        }
        else{
            //TODO
            String message= String.format("Your request to reschedule for course %s is not accepted!",course.getTopic());
//            emailSenderService.sendEmail(email,message,"Scheduling Acceptance");
        }
        rescheduleRepository.delete(course.getReschedule());

    }
}

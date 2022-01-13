package com.backend.schedulingsystem.domain.service;

import com.backend.schedulingsystem.domain.mappers.CourseMapper;
import com.backend.schedulingsystem.domain.model.dtos.CourseDto;
import com.backend.schedulingsystem.domain.model.dtos.InstructorDto;
import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.Instructor;
import com.backend.schedulingsystem.domain.repository.CourseRepository;
import com.backend.schedulingsystem.domain.repository.InstructorRepository;
import com.backend.schedulingsystem.domain.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    CourseRepository courseRepository;
    @Transactional(readOnly = true)
    @Override
    public InstructorDto getInstructorById(long id) {
        Instructor instructor = (Instructor) instructorRepository.findInstructorById(id);
        return UserMapper.<InstructorDto>entityToDto(instructor,new InstructorDto());
    }

    @Transactional(readOnly = true)
    @Override
    public InstructorDto getInstructorByEmail(String email) {
        Instructor instructor = (Instructor) instructorRepository.findInstructorByEmail(email);
        return UserMapper.<InstructorDto>entityToDto(instructor, new InstructorDto());
    }

    @Transactional
    @Override
    public void saveInstructor(InstructorDto instructorDto) {
        Instructor instructor = UserMapper.<Instructor>dtoToEntity(instructorDto,new Instructor());
        instructorRepository.save(instructor);
    }

    @Transactional
    @Override
    public void deleteInstructor(InstructorDto instructorDto) {
        Instructor instructor = UserMapper.<Instructor>dtoToEntity(instructorDto,new Instructor());
        instructorRepository.delete(instructor);
    }

    @Transactional
    @Override
    public void updateInstructor(InstructorDto instructorDto) {
        Instructor instructor = UserMapper.<Instructor>dtoToEntity(instructorDto,new Instructor());
        instructorRepository.save(instructor);

    }
    @Transactional
    @Override
    public List<Course> coursesGiven(long id) {
        Instructor instructor =  instructorRepository.findInstructorById(id);
        List<Course> courseList = instructor.getCourseList();

        return courseList;
    }

    @Override
    public List<CourseDto> coursesGivenByInstructor(String email) {
        Instructor instructor =  instructorRepository.findInstructorByEmail(email);
        List<CourseDto> courseDtos = new ArrayList<>();
        instructor.getCourseList().forEach(course -> {
            courseDtos.add(CourseMapper.entityToDto(course));
        });

        return courseDtos;
    }

    @Transactional
    @Override
    public List<InstructorDto> instructorList() throws ParseException {
        List<Instructor> instructors = instructorRepository.findAll();
        List<InstructorDto> instructorDtos = new ArrayList<>();
        instructors.forEach(instructor -> {
            List<CourseDto> activeCourses = new ArrayList<>();
            instructor.getCourseList().forEach(course -> {
                if(course.getCoursesTaken()==null || !course.isEnrolled()){
                    LocalDate localDate = LocalDate.now();
                    LocalTime time = LocalTime.now();
                    String t = time.format(DateTimeFormatter.ofPattern("HH:mm"));
                    Date date = null;
                    Date time1= null;
                    try {
                        date = new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
                        time1 = new SimpleDateFormat("HH:mm").parse(t);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(time1);
                    System.out.println(course.getStartTime());
                    if(date.before(course.getDate()))
                        activeCourses.add(CourseMapper.entityToDto(course));
                    else if(date.equals(course.getDate()) &&time1.before(course.getStartTime()))
                        activeCourses.add(CourseMapper.entityToDto(course));

                }
            });
            InstructorDto instructorDto = UserMapper.<InstructorDto>entityToDto(instructor,new InstructorDto());
            instructorDto.setCourseList(activeCourses);
            instructorDto.setPassword("");
            instructorDtos.add(instructorDto);
        });
        return instructorDtos;
    }
    @Override
    public List<CourseDto> coursesToAccept(String email){
        Instructor instructor = instructorRepository.findInstructorByEmail(email);
        List<CourseDto> courseDtos = new ArrayList<>();
        instructor.getCourseList().forEach(course -> {
            if(course.getCoursesTaken()!=null&& !course.isEnrolled()){
                courseDtos.add(CourseMapper.entityToDto(course));
            }
        });

        return courseDtos;
    }

    @Override
    public void cancelCourse(String email, long id) {

    }

    @Override
    public String createCourse(CourseDto courseDto,String email){

        Instructor instructor = instructorRepository.findInstructorByEmail(email);
        courseDto.setInstructor(UserMapper.<InstructorDto>entityToDto(instructor,new InstructorDto()));
        List<Course> courses = courseRepository.findAllWithinHours(courseDto.getStartTime(),courseDto.getEndTime(),courseDto.getDate());

        for(Course course: courses){
            if(course.getInstructor().getEmail().equals(instructor.getEmail())){
                return "A course exists within the given time period";
            }
        }

        courseDto.setEnrolled(false);

        Course course = CourseMapper.dtoToEntity(courseDto);
        courseRepository.save(course);
        return "success";


    }

    @Override
    public List<CourseDto> getRescheduleRequests(String email) {
        Instructor instructor = instructorRepository.findInstructorByEmail(email);
        List<CourseDto> courseDtos = new ArrayList<>();
        instructor.getCourseList().forEach(course -> {
            if(course.getReschedule()!=null && !course.getReschedule().getUser().getEmail().equals(email)){
                courseDtos.add(CourseMapper.entityToDto(course));
            }
        });

        return courseDtos;
    }


}

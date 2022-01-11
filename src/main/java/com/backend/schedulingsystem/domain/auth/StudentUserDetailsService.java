package com.backend.schedulingsystem.domain.auth;

import com.backend.schedulingsystem.domain.model.dtos.StudentDto;
import com.backend.schedulingsystem.domain.repository.StudentRepository;
import com.backend.schedulingsystem.domain.security.JwtUtil;
import com.backend.schedulingsystem.domain.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class StudentUserDetailsService implements UserDetailsService {
//    private StudentService studentService;
    @Autowired
    private JwtUtil jwtProvider;
    @Autowired
    StudentRepository studentRepository;
//    public StudentUserDetailsService(StudentService studentService){
//        this.studentService=studentService;
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        StudentDto student = studentService.getStudentByEmail(username);
        StudentDto student = UserMapper.entityToDto(studentRepository.findStudentByEmail(username),new StudentDto());
        if(student==null){
            throw new UsernameNotFoundException("WRONG EMAIL!");
        }else if(!student.isActive()){
            throw new UsernameNotFoundException("Is not active!");

        }

//        return new StudentDetails(student);

        return withUsername((student.getEmail()))
                .authorities(("STUDENT"))
                .password(student.getPassword()) //token does not have password but field may not be empty
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    public Optional<UserDetails> loadUserByJwtToken(String jwtToken) {
        if (jwtProvider.isValidToken(jwtToken)) {
            return Optional.of(
                    withUsername(jwtProvider.getUsername(jwtToken))
                            .authorities(jwtProvider.getRoles(jwtToken))
                            .password("") //token does not have password but field may not be empty
                            .accountExpired(false)
                            .accountLocked(false)
                            .credentialsExpired(false)
                            .disabled(false)
                            .build());
        }
        return Optional.empty();
    }
}

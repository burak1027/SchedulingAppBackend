package com.backend.schedulingsystem.domain.auth;

import com.backend.schedulingsystem.domain.model.dtos.InstructorDto;
import com.backend.schedulingsystem.domain.security.JwtUtil;
import com.backend.schedulingsystem.domain.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class InstructorUserDetailsService implements UserDetailsService {

    @Autowired
    InstructorService instructorService;
    @Autowired
    private JwtUtil jwtProvider;
    public InstructorUserDetailsService(InstructorService instructorService){
        this.instructorService=instructorService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        InstructorDto instructorDto = instructorService.getInstructorByEmail(username);
        if(instructorDto==null){
            throw new UsernameNotFoundException("WRONG EMAIL");
        }
        return withUsername((instructorDto.getEmail()))
                .authorities(("INSTRUCTOR"))
                .password(instructorDto.getPassword()) //token does not have password but field may not be empty
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

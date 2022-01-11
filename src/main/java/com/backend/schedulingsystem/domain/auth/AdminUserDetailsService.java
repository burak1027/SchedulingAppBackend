package com.backend.schedulingsystem.domain.auth;

import com.backend.schedulingsystem.domain.mappers.UserMapper;
import com.backend.schedulingsystem.domain.model.dtos.AdminDto;
import com.backend.schedulingsystem.domain.repository.AdminRepository;
import com.backend.schedulingsystem.domain.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;
@Service
public class AdminUserDetailsService implements UserDetailsService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    private JwtUtil jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminDto adminDto = UserMapper.entityToDto(adminRepository.findAdminByEmail(username),new AdminDto());
        return withUsername((adminDto.getEmail()))
                .authorities(("ADMIN"))
                .password(adminDto.getPassword()) //token does not have password but field may not be empty
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();    }
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

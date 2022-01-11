package com.backend.schedulingsystem.domain.auth;

import com.backend.schedulingsystem.domain.model.dtos.InstructorDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class InstructorDetails implements UserDetails {
    InstructorDto instructorDto;
    public InstructorDetails(InstructorDto instructorDto){
        this.instructorDto=instructorDto;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("INSTRUCTOR"));
    }

    @Override
    public String getPassword() {
        return instructorDto.getPassword();
    }

    @Override
    public String getUsername() {
        return instructorDto.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

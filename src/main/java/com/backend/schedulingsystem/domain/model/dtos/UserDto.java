package com.backend.schedulingsystem.domain.model.dtos;

import com.backend.schedulingsystem.domain.model.entity.Reschedule;

import javax.persistence.Column;
import java.util.List;

public class UserDto {
    private long id;
    private String name;
    private String Surname;
    private String email;
    private String Password;
    private boolean isActive;
    List<RescheduleDto> rescheduleDto;

    public List<RescheduleDto> getRescheduleDto() {
        return rescheduleDto;
    }

    public void setRescheduleDto(List<RescheduleDto> rescheduleDto) {
        this.rescheduleDto = rescheduleDto;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return Password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

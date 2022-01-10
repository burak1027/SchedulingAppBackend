package com.backend.schedulingsystem.domain.model.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "id")

public class Admin extends User{
}

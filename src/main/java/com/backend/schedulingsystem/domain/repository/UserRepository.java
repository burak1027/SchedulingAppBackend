package com.backend.schedulingsystem.domain.repository;

import com.backend.schedulingsystem.domain.model.entity.Course;
import com.backend.schedulingsystem.domain.model.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Primary
public interface UserRepository extends CrudRepository<User,Long> {
    User findUserById(long id);
    User findUserByEmail(String email);
    void deleteUserById(long id);
    void findUserByName(long id);

    @Query("select a from User a where a.isActive = false")
    List<User> inactiveUsers();

}

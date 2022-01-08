package com.backend.schedulingsystem.domain.repository;

import com.backend.schedulingsystem.domain.model.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface UserRepository extends CrudRepository<User,Long> {
    User findUserById(long id);
    User findUserByEmail(String email);
    void deleteUserById(long id);
    void findUserByName(long id);

}

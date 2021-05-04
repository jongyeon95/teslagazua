package com.jongyeon.teslagazua.repository;

import com.jongyeon.teslagazua.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}

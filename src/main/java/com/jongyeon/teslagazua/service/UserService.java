package com.jongyeon.teslagazua.service;

import com.jongyeon.teslagazua.entity.User;
import com.jongyeon.teslagazua.exception.EmailNotFoundException;
import com.jongyeon.teslagazua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserByEmail(String s){
        return userRepository.findByEmail(s).orElseThrow(()->new EmailNotFoundException());
    }

}

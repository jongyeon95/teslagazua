package com.jongyeon.teslagazua.service;

import com.jongyeon.teslagazua.entity.User;
import com.jongyeon.teslagazua.exception.EmailNotFoundException;
import com.jongyeon.teslagazua.exception.IdNotFoundException;
import com.jongyeon.teslagazua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User getUserByEmail(String s){
        return userRepository.findByEmail(s).orElseThrow(()->new EmailNotFoundException());
    }

    public  User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()->new IdNotFoundException());
    }

}

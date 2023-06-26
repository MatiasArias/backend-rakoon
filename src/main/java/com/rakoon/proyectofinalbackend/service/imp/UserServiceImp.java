package com.rakoon.proyectofinalbackend.service.imp;

import com.rakoon.proyectofinalbackend.model.entity.User;
import com.rakoon.proyectofinalbackend.repository.UserRepository;
import com.rakoon.proyectofinalbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}

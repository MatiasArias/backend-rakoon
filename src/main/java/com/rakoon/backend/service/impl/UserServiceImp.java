package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.User;
import com.rakoon.backend.repository.UserRepository;
import com.rakoon.backend.service.UserService;
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

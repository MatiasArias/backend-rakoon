package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.User;
import com.rakoon.backend.repository.UserRepository;
import com.rakoon.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User create(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User userPersisted = userRepository.save(user);
        log.info(String.format("User created successfully with id #%s", userPersisted.getUserId()));
        return userPersisted;
    }
}

package com.rakoon.backend.service.impl;

import com.rakoon.backend.model.entity.Consumer;
import com.rakoon.backend.model.entity.User;
import com.rakoon.backend.model.view.ConsumerDto;
import com.rakoon.backend.repository.ConsumerRepository;
import com.rakoon.backend.service.ConsumerService;
import com.rakoon.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ConsumerServiceImp implements ConsumerService {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    ConsumerRepository consumerRepository;
    @Autowired
    UserService userService;

    @Override
    public ConsumerDto save(ConsumerDto consumerDto) {
        Consumer consumerPersisted = saveConsumer(consumerDto);
        saveUser(consumerDto, consumerPersisted);
        consumerDto.setUserId(consumerPersisted.getConsumerId());
        return consumerDto;
    }

    private Consumer saveConsumer(ConsumerDto consumerDto) {
        Consumer consumer = modelMapper.map(consumerDto, Consumer.class);
        return consumerRepository.save(consumer);
    }

    private void saveUser(ConsumerDto consumerDto, Consumer consumer) {
        User user = modelMapper.map(consumerDto, User.class);
        user.setDateRegistration(LocalDate.now());
        user.setConsumerProfile(consumer);
        userService.save(user);
    }
}

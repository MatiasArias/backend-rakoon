package com.rakoon.proyectofinalbackend.service.imp;

import com.rakoon.proyectofinalbackend.model.entity.Consumer;
import com.rakoon.proyectofinalbackend.model.entity.User;
import com.rakoon.proyectofinalbackend.model.view.ConsumerDto;
import com.rakoon.proyectofinalbackend.repository.ConsumerRepository;
import com.rakoon.proyectofinalbackend.service.ConsumerService;
import com.rakoon.proyectofinalbackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        user.setConsumer(consumer);
        userService.save(user);
    }
}

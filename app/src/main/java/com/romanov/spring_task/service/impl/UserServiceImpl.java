package com.romanov.spring_task.service.impl;

import com.romanov.spring_task.exception.EntityExistsException;
import com.romanov.spring_task.mapper.UserMapper;
import com.romanov.spring_task.model.dto.UserInput;
import com.romanov.spring_task.model.dto.UserOutput;
import com.romanov.spring_task.model.entity.UserEntity;
import com.romanov.spring_task.repository.UserRepository;
import com.romanov.spring_task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserOutput create(UserInput input) {
        repository.findByNameAndEmail(input.getName(), input.getEmail())
                .ifPresent(value -> {
                    throw new EntityExistsException("User with username: " + input.getName() +
                            " and email: " + input.getEmail() + " already exists!");
                });
        UserEntity entity = new UserEntity();
        entity.setName(input.getName());
        entity.setEmail(input.getEmail());
        entity.setPassword(input.getPassword());
        entity.setCreationDate(LocalDate.now());
        return mapper.convert(repository.save(entity));
    }
}

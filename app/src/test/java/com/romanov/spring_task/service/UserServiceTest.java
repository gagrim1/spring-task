package com.romanov.spring_task.service;

import com.romanov.spring_task.mapper.UserMapper;
import com.romanov.spring_task.model.dto.UserInput;
import com.romanov.spring_task.model.dto.UserOutput;
import com.romanov.spring_task.model.entity.UserEntity;
import com.romanov.spring_task.repository.UserRepository;
import com.romanov.spring_task.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class UserServiceTest {
    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;
    private UserService service;

    private final String name = "name";
    private final String email = "email";
    private final String password = "password";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new UserServiceImpl(repository, mapper);
    }

    @Test
    void shouldCreateUser() {
        UserInput input = new UserInput(name,
                email,
                password);
        service.create(input);

        UserEntity entity = new UserEntity();
        entity.setName(name);
        entity.setPassword(password);
        entity.setEmail(email);
        entity.setCreationDate(LocalDate.now());

        Mockito.verify(repository, Mockito.times(1)).save(entity);
    }

    @Test
    void shouldNotCreateUser() {
        UserInput input = new UserInput(name,
                email,
                password);

        Mockito.when(repository.findByNameAndEmail(input.getName(), input.getEmail())).thenReturn(Optional.of(new UserEntity()));

        Assertions.assertThrows(RuntimeException.class, () -> service.create(input));
    }

}

package com.romanov.spring_task.mapper.impl;

import com.romanov.spring_task.mapper.UserMapper;
import com.romanov.spring_task.model.dto.UserOutput;
import com.romanov.spring_task.model.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UserMapperImpl implements UserMapper {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");

    @Override
    public UserEntity convert(UserOutput output) {
        UserEntity entity = new UserEntity();
        entity.setId(output.getId());
        entity.setName(output.getName());
        entity.setPassword(output.getPassword());
        entity.setEmail(output.getEmail());
        LocalDate localDate = LocalDate.parse(output.getCreationDate(), FORMATTER);
        entity.setCreationDate(localDate);
        return entity;
    }
}

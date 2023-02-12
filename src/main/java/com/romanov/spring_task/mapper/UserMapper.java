package com.romanov.spring_task.mapper;

import com.romanov.spring_task.model.dto.UserOutput;
import com.romanov.spring_task.model.entity.UserEntity;

public interface UserMapper {
    UserEntity convert(UserOutput output);
}

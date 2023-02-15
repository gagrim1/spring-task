package com.romanov.spring_task.service;

import com.romanov.spring_task.model.dto.UserInput;
import com.romanov.spring_task.model.dto.UserOutput;

public interface UserService {
    UserOutput create(UserInput input);
}

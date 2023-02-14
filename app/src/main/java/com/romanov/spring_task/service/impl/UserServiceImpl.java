package com.romanov.spring_task.service.impl;

import com.romanov.spring_task.model.dto.UserInput;
import com.romanov.spring_task.model.dto.UserOutput;
import com.romanov.spring_task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public UserOutput create(UserInput input) {
        return null;
    }
}

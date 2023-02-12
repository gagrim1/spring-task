package com.romanov.spring_task.endpoint.impl;

import com.romanov.spring_task.endpoint.UserEndpoint;
import com.romanov.spring_task.model.dto.UserInput;
import com.romanov.spring_task.model.dto.UserOutput;
import com.romanov.spring_task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserEndpointImpl implements UserEndpoint {
    private final UserService service;

    @Override
    @PostMapping
    public UserOutput create(UserInput input) {
        return service.create(input);
    }
}

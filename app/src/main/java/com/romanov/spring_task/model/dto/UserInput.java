package com.romanov.spring_task.model.dto;

import lombok.Value;

@Value
public class UserInput {
    String name;
    String email;
    String password;
}

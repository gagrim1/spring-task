package com.romanov.spring_task.model.dto;

import lombok.Value;

@Value
public class UserOutput {
    Long id;
    String name;
    String email;
    String password;
    String creationDate;
}

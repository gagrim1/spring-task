package com.romanov.spring_task.endpoint;

import com.romanov.spring_task.model.dto.UserInput;
import com.romanov.spring_task.model.dto.UserOutput;

public interface UserEndpoint {
    UserOutput create(UserInput input);
}

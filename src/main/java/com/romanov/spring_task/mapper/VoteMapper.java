package com.romanov.spring_task.mapper;

import com.romanov.spring_task.model.dto.VoteOutput;
import com.romanov.spring_task.model.entity.VoteEntity;

public interface VoteMapper {
    VoteEntity convert(VoteOutput output);
}

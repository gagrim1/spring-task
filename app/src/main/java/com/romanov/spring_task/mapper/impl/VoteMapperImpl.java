package com.romanov.spring_task.mapper.impl;

import com.romanov.spring_task.mapper.VoteMapper;
import com.romanov.spring_task.model.dto.VoteOutput;
import com.romanov.spring_task.model.entity.VoteEntity;
import org.springframework.stereotype.Component;

@Component
public class VoteMapperImpl implements VoteMapper {
    @Override
    public VoteEntity convert(VoteOutput output) {
        VoteEntity entity = new VoteEntity();
        entity.setId(output.getId());
        entity.setName(output.getName());
        return entity;
    }
}

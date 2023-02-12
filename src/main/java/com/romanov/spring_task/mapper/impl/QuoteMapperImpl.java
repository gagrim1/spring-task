package com.romanov.spring_task.mapper.impl;

import com.romanov.spring_task.mapper.QuoteMapper;
import com.romanov.spring_task.model.dto.QuoteOutput;
import com.romanov.spring_task.model.entity.QuoteEntity;
import org.springframework.stereotype.Component;

@Component
public class QuoteMapperImpl implements QuoteMapper {
    @Override
    public QuoteOutput convert(QuoteEntity entity) {
        return new QuoteOutput(entity.getId(),
                entity.getContent(),
                entity.getUpdateDate().toString(),
                entity.getUser().getName(),
                entity.getVote().getName());
    }
}

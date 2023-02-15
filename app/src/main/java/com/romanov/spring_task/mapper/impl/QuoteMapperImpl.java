package com.romanov.spring_task.mapper.impl;

import com.romanov.spring_task.mapper.QuoteMapper;
import com.romanov.spring_task.model.dto.QuoteOutput;
import com.romanov.spring_task.model.entity.QuoteEntity;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class QuoteMapperImpl implements QuoteMapper {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");

    @Override
    public QuoteOutput convert(QuoteEntity entity) {
        return new QuoteOutput(entity.getId(),
                entity.getContent().getName(),
                entity.getUpdateDate().format(FORMATTER),
                entity.getUser().getName(),
                entity.getVote().getName());
    }
}

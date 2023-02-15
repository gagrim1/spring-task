package com.romanov.spring_task.mapper;

import com.romanov.spring_task.model.dto.QuoteOutput;
import com.romanov.spring_task.model.entity.QuoteEntity;

public interface QuoteMapper {
    QuoteOutput convert(QuoteEntity entity);
}

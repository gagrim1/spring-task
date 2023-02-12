package com.romanov.spring_task.service;

import com.romanov.spring_task.model.dto.QuoteInput;
import com.romanov.spring_task.model.dto.QuoteOutput;
import com.romanov.spring_task.model.dto.QuoteUpdateInput;
import org.springframework.data.domain.Page;

public interface QuoteService {
    QuoteOutput add(QuoteInput input);
    Page<QuoteOutput> getAll(Integer page, Integer size);
    QuoteOutput getRandomQuote();
    QuoteOutput modifyQuote(QuoteUpdateInput input);
    void delete(Long id);
}

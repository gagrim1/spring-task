package com.romanov.spring_task.endpoint;

import com.romanov.spring_task.model.dto.QuoteInput;
import com.romanov.spring_task.model.dto.QuoteOutput;
import com.romanov.spring_task.model.dto.QuoteUpdateInput;
import org.springframework.data.domain.Page;

public interface QuoteEndpoint {
    QuoteOutput add(QuoteInput input);
    Page<QuoteOutput> getAllAsc(Integer page, Integer size);
    Page<QuoteOutput> getAllDesc(Integer page, Integer size);
    QuoteOutput getRandomQuote();
    QuoteOutput modifyQuote(QuoteUpdateInput input);
    void delete(Long id);
}

package com.romanov.spring_task.service;

import com.romanov.spring_task.model.dto.QuoteInput;
import com.romanov.spring_task.model.dto.QuoteOutput;
import com.romanov.spring_task.model.dto.QuoteUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface QuoteService {
    QuoteOutput add(QuoteInput input);
    List<QuoteOutput> getAllByVoteId(Long voteId);
    Page<QuoteOutput> getAll(Integer page, Integer size, Sort sort);
    QuoteOutput getRandomQuote();
    QuoteOutput modifyQuote(QuoteUpdateInput input);
    void delete(Long id);
}

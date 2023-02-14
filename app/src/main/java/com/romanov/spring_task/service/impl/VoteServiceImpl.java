package com.romanov.spring_task.service.impl;

import com.romanov.spring_task.model.dto.QuoteOutput;
import com.romanov.spring_task.model.entity.VoteEntity;
import com.romanov.spring_task.model.enumurated.Content;
import com.romanov.spring_task.repository.VoteRepository;
import com.romanov.spring_task.service.QuoteService;
import com.romanov.spring_task.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final VoteRepository repository;
    private final QuoteService quoteService;

    @Override
    public void create(String name) {
        VoteEntity entity = new VoteEntity();
        entity.setName(name);
        repository.save(entity);
    }

    @Override
    public Boolean isVotedFor(Long voteId) {
        List<QuoteOutput> quotes = quoteService.getAllByVoteId(voteId);
        Long upvote = quotes.stream().filter(value -> value.getContent().equals(Content.UPVOTE.name())).count();
        Long downvote = quotes.stream().filter(value -> value.getContent().equals(Content.DOWNVOTE.name())).count();
        return upvote > downvote;
    }
}

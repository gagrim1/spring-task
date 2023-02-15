package com.romanov.spring_task.service;

import com.romanov.spring_task.model.dto.QuoteOutput;
import com.romanov.spring_task.model.entity.VoteEntity;
import com.romanov.spring_task.model.enumurated.Content;
import com.romanov.spring_task.repository.VoteRepository;
import com.romanov.spring_task.service.impl.VoteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VoteServiceTest {
    @Mock
    private VoteRepository repository;
    @Mock
    private QuoteService quoteService;

    private VoteService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new VoteServiceImpl(repository, quoteService);
    }

    @Test
    void shouldCreateVote() {
        service.create("name");

        VoteEntity entity = new VoteEntity();
        entity.setName("name");

        Mockito.verify(repository, Mockito.times(1)).save(entity);
    }

    @Test
    void shouldCheckIsVotedFor() {
        QuoteOutput upvote = new QuoteOutput(1L,
                Content.UPVOTE.name(),
                LocalDate.now().toString(),
                "user",
                "name");
        QuoteOutput downvote = new QuoteOutput(1L,
                Content.DOWNVOTE.name(),
                LocalDate.now().toString(),
                "user",
                "name");
        List<QuoteOutput> quotes = new ArrayList<>();
        quotes.add(upvote);
        quotes.add(upvote);
        quotes.add(upvote);
        quotes.add(downvote);

        Mockito.when(quoteService.getAllByVoteId(1L)).thenReturn(quotes);
        Boolean actual = service.isVotedFor(1L);

        Assertions.assertTrue(actual);
    }

    @Test
    void shouldCheckIsVotedAgainst() {
        QuoteOutput downvote = new QuoteOutput(1L,
                Content.DOWNVOTE.name(),
                LocalDate.now().toString(),
                "user",
                "name");
        QuoteOutput upvote = new QuoteOutput(1L,
                Content.UPVOTE.name(),
                LocalDate.now().toString(),
                "user",
                "name");
        List<QuoteOutput> quotes = new ArrayList<>();
        quotes.add(downvote);
        quotes.add(downvote);
        quotes.add(downvote);
        quotes.add(upvote);

        Mockito.when(quoteService.getAllByVoteId(1L)).thenReturn(quotes);
        Boolean actual = service.isVotedFor(1L);

        Assertions.assertFalse(actual);
    }
}

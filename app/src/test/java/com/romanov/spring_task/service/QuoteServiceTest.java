package com.romanov.spring_task.service;

import com.romanov.spring_task.exception.EntityNotFoundException;
import com.romanov.spring_task.mapper.QuoteMapper;
import com.romanov.spring_task.mapper.UserMapper;
import com.romanov.spring_task.mapper.VoteMapper;
import com.romanov.spring_task.model.dto.QuoteInput;
import com.romanov.spring_task.model.dto.QuoteOutput;
import com.romanov.spring_task.model.dto.UserOutput;
import com.romanov.spring_task.model.dto.VoteOutput;
import com.romanov.spring_task.model.entity.QuoteEntity;
import com.romanov.spring_task.model.enumurated.Content;
import com.romanov.spring_task.repository.QuoteRepository;
import com.romanov.spring_task.service.impl.QuoteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuoteServiceTest {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");

    @Mock
    private QuoteRepository repository;
    @Mock
    private QuoteMapper mapper;
    @Mock
    private UserMapper userMapper;
    @Mock
    private VoteMapper voteMapper;
    private QuoteService service;

    private final VoteOutput voteOutput = new VoteOutput(1L, "name");
    private final QuoteOutput quoteOutput = new QuoteOutput(1L,
            Content.UPVOTE.name(),
            LocalDate.now().format(FORMATTER),
            "username",
            "voteName");
    private final UserOutput userOutput = new UserOutput(1L,
            "name",
            "email",
            "password",
            LocalDate.now().toString());
    private final QuoteInput input = new QuoteInput((short) 1,
            userOutput, voteOutput);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new QuoteServiceImpl(repository,
                mapper,
                userMapper,
                voteMapper);
    }

    @Test
    void shouldAddQuote() {
        QuoteEntity entity = new QuoteEntity();
        entity.setContent(Content.getById(input.getContentId()));
        entity.setUpdateDate(LocalDate.now());
        entity.setUser(userMapper.convert(input.getUser()));
        entity.setVote(voteMapper.convert(input.getVote()));

        service.add(input);
        Mockito.verify(repository, Mockito.times(1)).save(entity);
    }

    @Test
    void shouldAddExistsQuote() {
        Mockito.when(repository.findByUserIdAndVoteId(1L, 1L)).thenReturn(Optional.of(new QuoteEntity()));

        Assertions.assertThrows(RuntimeException.class, () -> service.add(input));
    }

    @Test
    void shouldReturnQuotesByVoteId() {
        QuoteEntity entity = new QuoteEntity();
        List<QuoteEntity> quoteEntities = new ArrayList<>();
        quoteEntities.add(entity);
        quoteEntities.add(entity);
        quoteEntities.add(entity);
        List<QuoteOutput> quoteOutputs = new ArrayList<>();
        quoteOutputs.add(quoteOutput);
        quoteOutputs.add(quoteOutput);
        quoteOutputs.add(quoteOutput);

        Mockito.when(repository.findAllByVoteId(1L)).thenReturn(quoteEntities);
        Mockito.when(mapper.convert(entity)).thenReturn(quoteOutput);

        List<QuoteOutput> actual = service.getAllByVoteId(1L);
        Assertions.assertEquals(quoteOutputs, actual);
    }

    @Test
    void shouldNotReturnQuotesByVoteId() {
        List<QuoteOutput> expected = new ArrayList<>();

        List<QuoteEntity> quoteEntities = new ArrayList<>();
        Mockito.when(repository.findAllByVoteId(1L)).thenReturn(quoteEntities);
        Assertions.assertEquals(expected, service.getAllByVoteId(1L));
    }

    @Test
    void shouldReturnAllQuotes() {
        List<QuoteOutput> expected = new ArrayList<>();

        expected.add(quoteOutput);
        QuoteEntity entity = new QuoteEntity();
        List<QuoteEntity> mockList = new ArrayList<>();
        mockList.add(entity);

        Mockito.when(repository.findAllByVoteId(1L)).thenReturn(mockList);
        Mockito.when(mapper.convert(entity)).thenReturn(quoteOutput);

        Assertions.assertEquals(expected, service.getAllByVoteId(1L));
    }

    @Test
    void shouldReturnRandomQuote() {
        QuoteEntity entity = new QuoteEntity();
        List<QuoteEntity> mockQuotes = new ArrayList<>();
        mockQuotes.add(entity);
        Mockito.when(repository.findAll()).thenReturn(mockQuotes);
        Mockito.when(mapper.convert(entity)).thenReturn(quoteOutput);

        Assertions.assertEquals(quoteOutput, service.getRandomQuote());
    }

    @Test
    void shouldDeleteQuote() {
        Long id = 1L;
        QuoteEntity entity = new QuoteEntity();
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entity));
        service.delete(id);
        Mockito.verify(repository, Mockito.times(1)).delete(entity);
    }

    @Test
    void shouldNotDeleteQuote() {
        Long id = 1L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> service.delete(id));
    }
}

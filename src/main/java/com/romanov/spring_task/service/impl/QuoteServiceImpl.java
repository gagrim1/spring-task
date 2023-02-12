package com.romanov.spring_task.service.impl;

import com.romanov.spring_task.exception.EntityNotFoundException;
import com.romanov.spring_task.mapper.QuoteMapper;
import com.romanov.spring_task.mapper.UserMapper;
import com.romanov.spring_task.mapper.VoteMapper;
import com.romanov.spring_task.model.dto.QuoteInput;
import com.romanov.spring_task.model.dto.QuoteOutput;
import com.romanov.spring_task.model.dto.QuoteUpdateInput;
import com.romanov.spring_task.model.entity.QuoteEntity;
import com.romanov.spring_task.repository.QuoteRepository;
import com.romanov.spring_task.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository repository;
    private final QuoteMapper mapper;
    private final UserMapper userMapper;
    private final VoteMapper voteMapper;

    @Override
    public QuoteOutput add(QuoteInput input) {
        QuoteEntity entity = new QuoteEntity();
        entity.setContent(input.getContent());
        entity.setUpdateDate(LocalDate.now());
        entity.setUser(userMapper.convert(input.getUser()));
        entity.setVote(voteMapper.convert(input.getVote()));
        return mapper.convert(repository.save(entity));
    }

    @Override
    public Page<QuoteOutput> getAll(Integer page, Integer size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size);
        List<QuoteOutput> list = repository.findAll(sort)
                .stream()
                .map(mapper::convert)
                .toList();
        return new PageImpl<>(list, pageable, list.size());
    }

    @Override
    public QuoteOutput getRandomQuote() {
        List<QuoteEntity> list = repository.findAll();
        Long randomIndex = new Random()
                .nextLong(list.size());
        QuoteEntity randomQuote = repository.findAll().get(randomIndex.intValue());

        return mapper.convert(randomQuote);
    }

    @Override
    public QuoteOutput modifyQuote(QuoteUpdateInput input) {
        QuoteEntity entity = repository.findById(input.getId())
                .orElseThrow(() -> new EntityNotFoundException("Entity with id: " + input.getId() + " not found!"));
        entity.setContent(input.getContent());
        entity.setUpdateDate(LocalDate.now());
        entity.setUser(userMapper.convert(input.getUser()));
        entity.setVote(voteMapper.convert(input.getVote()));
        return mapper.convert(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

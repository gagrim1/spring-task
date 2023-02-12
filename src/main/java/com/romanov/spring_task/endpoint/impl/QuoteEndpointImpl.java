package com.romanov.spring_task.endpoint.impl;

import com.romanov.spring_task.endpoint.QuoteEndpoint;
import com.romanov.spring_task.model.dto.QuoteInput;
import com.romanov.spring_task.model.dto.QuoteOutput;
import com.romanov.spring_task.model.dto.QuoteUpdateInput;
import com.romanov.spring_task.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quotes")
@RequiredArgsConstructor
public class QuoteEndpointImpl implements QuoteEndpoint {
    private final QuoteService service;

    @Override
    @PostMapping
    public QuoteOutput add(@RequestBody QuoteInput input) {
        return service.add(input);
    }

    @Override
    @GetMapping("/all-asc")
    public Page<QuoteOutput> getAllAsc(Integer page, Integer size) {
        return service.getAll(page, size, Sort.by(Sort.Direction.ASC, "updateDate"));
    }

    @Override
    @GetMapping("/all-desc")
    public Page<QuoteOutput> getAllDesc(Integer page, Integer size) {
        return service.getAll(page, size, Sort.by(Sort.Direction.DESC, "updateDate"));
    }

    @Override
    @GetMapping
    public QuoteOutput getRandomQuote() {
        return service.getRandomQuote();
    }

    @Override
    @PatchMapping
    public QuoteOutput modifyQuote(@RequestBody QuoteUpdateInput input) {
        return service.modifyQuote(input);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}

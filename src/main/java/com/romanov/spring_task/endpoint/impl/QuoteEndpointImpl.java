package com.romanov.spring_task.endpoint.impl;

import com.romanov.spring_task.endpoint.QuoteEndpoint;
import com.romanov.spring_task.model.dto.QuoteInput;
import com.romanov.spring_task.model.dto.QuoteOutput;
import com.romanov.spring_task.model.dto.QuoteUpdateInput;
import com.romanov.spring_task.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    @GetMapping("/all")
    public Page<QuoteOutput> getAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<QuoteOutput> pages = service.getAll(page, size);
        return service.getAll(page, size);
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

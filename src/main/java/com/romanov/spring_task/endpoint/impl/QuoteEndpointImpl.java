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

    /**
     * Saves a given input data for quote to database
     *
     * @param input - Data for modify quote. Contains content of quote, data of user who add quote and vote
     * @return QuoteOutput - data of saved quote
     */
    @Override
    @PostMapping
    public QuoteOutput add(@RequestBody QuoteInput input) {
        return service.add(input);
    }

    /**
     * Returns all quotes sorted asc by the date
     *
     * @param page – zero-based page index, must not be negative
     * @param size - the size of the page to be returned, must be greater than 0
     * @return Returns a Page of quotes
     */
    @Override
    @GetMapping("/all-asc")
    public Page<QuoteOutput> getAllAsc(@RequestParam Integer page, @RequestParam Integer size) {
        return service.getAll(page, size, Sort.by(Sort.Direction.ASC, "updateDate"));
    }

    /**
     * Returns all quotes sorted desc by the date
     *
     * @param page – zero-based page index, must not be negative
     * @param size - the size of the page to be returned, must be greater than 0
     * @return Returns a Page of quotes
     */
    @Override
    @GetMapping("/all-desc")
    public Page<QuoteOutput> getAllDesc(@RequestParam Integer page, @RequestParam Integer size) {
        return service.getAll(page, size, Sort.by(Sort.Direction.DESC, "updateDate"));
    }

    /**
     * Returns random existing quote from database
     *
     * @return QuoteOutput - data of random quote
     */
    @Override
    @GetMapping
    public QuoteOutput getRandomQuote() {
        return service.getRandomQuote();
    }

    /**
     * Modify the quote with the given input data
     *
     * @param input - Data for modify quote. Contains quote's id, content of quote, data of user who add quote and vote
     * @return QuoteOutput - quote after modifying
     */
    @Override
    @PatchMapping
    public QuoteOutput modifyQuote(@RequestBody QuoteUpdateInput input) {
        return service.modifyQuote(input);
    }

    /**
     * Deletes the quote with the given id
     *
     * @param id - must not be null
     */
    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}

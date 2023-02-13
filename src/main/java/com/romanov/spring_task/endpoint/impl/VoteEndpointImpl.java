package com.romanov.spring_task.endpoint.impl;

import com.romanov.spring_task.endpoint.VoteEndpoint;
import com.romanov.spring_task.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VoteEndpointImpl implements VoteEndpoint {
    private final VoteService service;

    /**
     * Saves a given input data of vote to database
     *
     * @param name - name of voting
     */
    @Override
    @PostMapping
    public void create(String name) {
        service.create(name);
    }

    /**
     * Check result of voting
     *
     * @param voteId - id of voting
     * @return true if there were more votes for than against
     */
    @Override
    @GetMapping("/{voteId}")
    public Boolean isVotedFor(@PathVariable Long voteId) {
        return service.isVotedFor(voteId);
    }
}

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

    @Override
    @PostMapping
    public void create(String name) {
        service.create(name);
    }

    @Override
    @GetMapping("/{voteId}")
    public Boolean isVotedFor(@PathVariable Long voteId) {
        return service.isVotedFor(voteId);
    }
}

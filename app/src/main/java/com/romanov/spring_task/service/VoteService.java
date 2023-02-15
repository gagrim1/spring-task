package com.romanov.spring_task.service;

public interface VoteService {
    void create(String name);
    Boolean isVotedFor(Long voteId);
}

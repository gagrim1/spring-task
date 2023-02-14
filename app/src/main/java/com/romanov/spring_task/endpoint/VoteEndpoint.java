package com.romanov.spring_task.endpoint;

public interface VoteEndpoint {
    void create(String name);
    Boolean isVotedFor(Long voteId);
}

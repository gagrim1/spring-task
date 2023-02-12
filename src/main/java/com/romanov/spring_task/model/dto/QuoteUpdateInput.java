package com.romanov.spring_task.model.dto;

import lombok.Value;

@Value
public class QuoteUpdateInput {
    Long id;
    String content;
    UserOutput user;
    VoteOutput vote;
}

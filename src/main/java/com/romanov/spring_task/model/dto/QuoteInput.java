package com.romanov.spring_task.model.dto;

import lombok.Value;

@Value
public class QuoteInput {
    String content;
    UserOutput user;
    VoteOutput vote;
}

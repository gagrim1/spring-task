package com.romanov.spring_task.model.dto;

import lombok.Value;

@Value
public class QuoteInput {
    Short contentId;
    UserOutput user;
    VoteOutput vote;
}

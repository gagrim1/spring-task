package com.romanov.spring_task.model.dto;

import lombok.Value;

@Value
public class QuoteUpdateInput {
    Long id;
    Short contentId;
    UserOutput user;
    VoteOutput vote;
}

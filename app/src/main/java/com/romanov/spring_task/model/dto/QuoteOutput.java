package com.romanov.spring_task.model.dto;

import lombok.Value;

@Value
public class QuoteOutput {
    Long id;
    String content;
    String updateDate;
    String username;
    String voteName;
}

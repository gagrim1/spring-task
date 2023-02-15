package com.romanov.spring_task.mapper;

import com.romanov.spring_task.mapper.impl.QuoteMapperImpl;
import com.romanov.spring_task.model.dto.QuoteOutput;
import com.romanov.spring_task.model.entity.QuoteEntity;
import com.romanov.spring_task.model.entity.UserEntity;
import com.romanov.spring_task.model.entity.VoteEntity;
import com.romanov.spring_task.model.enumurated.Content;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class QuoteMapperTest {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");

    private QuoteMapper mapper = new QuoteMapperImpl();

    @Test
    void shouldConvertEntity() {
        QuoteOutput expected = new QuoteOutput(1L,
                Content.UPVOTE.name(),
                LocalDate.now().format(FORMATTER),
                "user",
                "vote");
        UserEntity userEntity = new UserEntity();
        userEntity.setName("user");
        VoteEntity voteEntity = new VoteEntity();
        voteEntity.setName("vote");
        QuoteEntity entity = new QuoteEntity();
        entity.setId(1L);
        entity.setContent(Content.UPVOTE);
        String dateString = LocalDate.now().format(FORMATTER);
        entity.setUpdateDate(LocalDate.parse(dateString, FORMATTER));
        entity.setUser(userEntity);
        entity.setVote(voteEntity);

        QuoteOutput actual = mapper.convert(entity);

        Assertions.assertEquals(expected, actual);
    }
}

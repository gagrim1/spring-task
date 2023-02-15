package com.romanov.spring_task.mapper;

import com.romanov.spring_task.mapper.impl.VoteMapperImpl;
import com.romanov.spring_task.model.dto.VoteOutput;
import com.romanov.spring_task.model.entity.VoteEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VoteMapperTest {
    private VoteMapper mapper = new VoteMapperImpl();
    @Test
    void shouldConvertVote() {
        VoteEntity expected = new VoteEntity();
        expected.setId(1L);
        expected.setName("name");
        VoteOutput output = new VoteOutput(1L, "name");

        VoteEntity actual = mapper.convert(output);

        Assertions.assertEquals(expected, actual);
    }
}

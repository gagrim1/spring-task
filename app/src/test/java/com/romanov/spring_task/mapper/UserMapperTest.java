package com.romanov.spring_task.mapper;

import com.romanov.spring_task.mapper.impl.UserMapperImpl;
import com.romanov.spring_task.model.dto.UserOutput;
import com.romanov.spring_task.model.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserMapperTest {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");

    private UserMapper mapper = new UserMapperImpl();

    @Test
    void shouldConvertUserEntity() {
        UserOutput expected = new UserOutput(1L,
                "name",
                "user@mail.com",
                "password",
                LocalDate.now().format(FORMATTER)
        );
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setName("name");
        entity.setEmail("user@mail.com");
        entity.setPassword("password");
        entity.setCreationDate(LocalDate.now());

        UserOutput actual = mapper.convert(entity);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldConvertUserOutput() {
        UserEntity expected = new UserEntity();
        expected.setId(1L);
        expected.setName("name");
        expected.setEmail("user@mail.com");
        expected.setPassword("password");
        expected.setCreationDate(LocalDate.now());
        UserOutput output = new UserOutput(1L,
                "name",
                "user@mail.com",
                "password",
                LocalDate.now().format(FORMATTER)
                );

        UserEntity actual = mapper.convert(output);

        Assertions.assertEquals(expected, actual);
    }
}

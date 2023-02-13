package com.romanov.spring_task.endpoint;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(value = {
        "/endpoint/vote_init.sql",
        "/endpoint/user_init.sql",
        "/endpoint/quote_init.sql"
},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/endpoint/truncate.sql",
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class VoteEndpointTest extends EndpointTest {
    @Test
    void shouldAddQuote() throws Exception {
        mockMvc.perform(post("/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "vote1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCheckIsUpvote() throws Exception {
        mockMvc.perform(get("/votes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }
}

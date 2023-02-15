package com.romanov.spring_task.endpoint;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(value = {
        "/endpoint/sql/user_init.sql"
},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/endpoint/sql/truncate.sql",
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserEndpointTest extends EndpointTest {
    @Test
    void shouldAddQuote() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fromFile("/endpoint/json/user_input.json")))
                .andExpect(status().isOk());
    }
}

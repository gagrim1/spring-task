package com.romanov.spring_task.endpoint;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
public class QuoteEndpointTest extends EndpointTest {
    @Test
    void shouldAddQuote() throws Exception {
        mockMvc.perform(get("/quotes/all-desc").param("page", "1").param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].id").value(1))
                .andExpect(jsonPath("$.content.[1].id").value(2))
                .andExpect(jsonPath("$.content.[2].id").value(3))
                .andExpect(jsonPath("$.content.[3].id").doesNotExist());

        mockMvc.perform(post("/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fromFile("/endpoint/json/quote_input.json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/quotes/all-asc").param("page", "1").param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].id").value(1))
                .andExpect(jsonPath("$.content.[1].id").value(2))
                .andExpect(jsonPath("$.content.[2].id").value(3))
                .andExpect(jsonPath("$.content.[3].id").value(4));
    }

    @Test
    void shouldPatchQuote() throws Exception {
        mockMvc.perform(get("/quotes/all-desc").param("page", "1").param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].id").value(1))
                .andExpect(jsonPath("$.content.[0].content").value("UPVOTE"));

        mockMvc.perform(patch("/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fromFile("/endpoint/json/quote_patch_input.json")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/quotes/all-desc").param("page", "1").param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].id").value(1))
                .andExpect(jsonPath("$.content.[0].content").value("UPVOTE"));
    }

    @Test
    void shouldReturnRandomQuote() throws Exception {
        mockMvc.perform(get("/quotes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.updateDate").exists())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.voteName").exists());
    }


    @Test
    void shouldReturnQuotes() throws Exception {
        mockMvc.perform(get("/quotes/all-desc").param("page", "1").param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].id").value(1))
                .andExpect(jsonPath("$.content.[1].id").value(2))
                .andExpect(jsonPath("$.content.[2].id").value(3));
    }

    @Test
    void shouldDeleteQuote() throws Exception {
        mockMvc.perform(get("/quotes/all-desc").param("page", "1").param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].id").value(1))
                .andExpect(jsonPath("$.content.[1].id").value(2))
                .andExpect(jsonPath("$.content.[2].id").value(3));

        mockMvc.perform(delete("/quotes/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/quotes/all-desc").param("page", "1").param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].id").value(1))
                .andExpect(jsonPath("$.content.[1].id").value(3))
                .andExpect(jsonPath("$.content.[2].id").doesNotExist());
    }
}

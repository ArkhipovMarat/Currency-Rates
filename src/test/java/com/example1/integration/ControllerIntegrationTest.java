package com.example1.integration;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    private static final String VALID_REQUEST = "/rates/USD";
    private static final String INDEX_VIEW_NAME = "index";
    private static final String MODEL_ATTRIBUTE = "gif";
    private static final String REGEX = "https://" + "media" + "(\\w*)" + ".giphy.com/media/" + "(\\w*)" + "/giphy.gif";

    private static final String BAD_REQUEST = "/";
    private final String MESSAGE_BAD_REQUEST = "Bad request! Please type valid request. Example: rates/USD";

    @Test
    void getCurrencyGiphyWhenValidRequest() throws Exception {
        mockMvc.perform(get(VALID_REQUEST))
                .andExpect(view().name(INDEX_VIEW_NAME))
                .andExpect(model().attribute(MODEL_ATTRIBUTE, Matchers.matchesRegex(REGEX)));
    }

    @Test
    void getCurrencyGiphyWhenBadRequest() throws Exception {
        mockMvc.perform(get(BAD_REQUEST))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(MESSAGE_BAD_REQUEST));
    }
}

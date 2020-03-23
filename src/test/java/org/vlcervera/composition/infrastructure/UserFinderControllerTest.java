package org.vlcervera.composition.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.vlcervera.composition.application.response.UserResponse;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserFinderController.class)
public class UserFinderControllerTest {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void ass() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                                               .get("/v1/test")
                                               .accept(MediaType.APPLICATION_JSON))
                              .andDo(MockMvcResultHandlers.print())
                              .andExpect(status().isOk())
                              .andReturn();

        String content = result.getResponse().getContentAsString();

        UserResponse user = objectMapper.readValue(content, UserResponse.class);
        Assertions.assertThat(user).isNotNull();

    }
}
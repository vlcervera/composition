package org.vlcervera.composition.infrastructure.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.vlcervera.composition.application.response.UserResponse;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserFinderControllerTest {

    @Autowired
    private MockMvc      mvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of("/users/concurrent", 5),
                Arguments.of("/users/sequential", 20)
                        );
    }

    @ParameterizedTest
    @MethodSource("provider")
    public void shouldReturnAnUser(String endpoint, long timeoutExpecedInSeconds) throws Exception {
        LocalDateTime start = LocalDateTime.now();
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                                               .get(endpoint)
                                               .accept(MediaType.APPLICATION_JSON))
                              .andDo(MockMvcResultHandlers.print())
                              .andExpect(status().isOk())
                              .andReturn();

        LocalDateTime end             = LocalDateTime.now();
        long          secondsResponse = start.until(end, ChronoUnit.SECONDS);

        String content = result.getResponse().getContentAsString();

        UserResponse user = objectMapper.readValue(content, UserResponse.class);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(secondsResponse).isEqualTo(timeoutExpecedInSeconds);

    }

}
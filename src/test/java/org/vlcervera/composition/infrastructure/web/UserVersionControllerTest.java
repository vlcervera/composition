package org.vlcervera.composition.infrastructure.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserVersionControllerTest {
    @Autowired
    private MockMvc      mvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(Versions.v1),
                Arguments.of(Versions.v2),
                Arguments.of(Versions.v3)
                        );
    }

    @ParameterizedTest
    @MethodSource("provider")
    public void shouldReturnVersionRequested(Versions versionsToTest) throws Exception {
        String baseEndpoint = "/test-version";
        String endpoint     = "/" + versionsToTest.name() + baseEndpoint;

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                                               .get(endpoint)
                                               .accept(MediaType.APPLICATION_JSON))
                              .andDo(MockMvcResultHandlers.print())
                              .andExpect(status().isOk())

                              .andReturn();

        Versions versionReceived = objectMapper.readValue(result.getResponse().getContentAsString(), Versions.class);

        Assertions.assertThat(versionReceived).isEqualTo(versionsToTest);
    }
}
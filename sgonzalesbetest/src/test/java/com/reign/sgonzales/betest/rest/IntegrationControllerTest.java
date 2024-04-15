package com.reign.sgonzales.betest.rest;

import com.reign.sgonzales.betest.service.IntegrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class IntegrationControllerTest {
    @Mock
    private IntegrationService integrationService;
    private MockMvc client;

    @BeforeEach
    void setup() {
        var integrationController = new IntegrationController(integrationService);
        client = MockMvcBuilders.standaloneSetup(integrationController)
                .build();
    }

    @DisplayName("Get news")
    @Test
    public void testIntegration() throws Exception {
        MvcResult mvcResult = client.perform(MockMvcRequestBuilders.post("/v1/integration")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        Assertions.assertEquals(mvcResult.getResponse().getStatus(), 204);
    }
}

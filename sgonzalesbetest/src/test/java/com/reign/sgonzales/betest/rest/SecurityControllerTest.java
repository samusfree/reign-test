package com.reign.sgonzales.betest.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.reign.sgonzales.betest.dto.LoginRequestDTO;
import com.reign.sgonzales.betest.dto.LoginResponseDTO;
import com.reign.sgonzales.betest.service.JWTService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class SecurityControllerTest {
    @Mock
    private JWTService jwtService;
    private MockMvc client;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = JsonMapper.builder()
                .findAndAddModules()
                .build();
        var securityController = new SecurityController(jwtService);
        client = MockMvcBuilders.standaloneSetup(securityController)
                .build();
    }

    @DisplayName("Login test")
    @Test
    void login() throws Exception {
        Mockito.when(jwtService.getJWTToken(any(), any())).thenReturn("token");
        var loginRequestDTO = new LoginRequestDTO("user", "password");
        MvcResult mvcResult = client.perform(MockMvcRequestBuilders.post("/v1/security/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(loginRequestDTO))).andReturn();
        LoginResponseDTO responseDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), LoginResponseDTO.class);
        Assertions.assertEquals(mvcResult.getResponse().getStatus(), 200);
        Assertions.assertEquals(responseDTO.token(), "token");
        Assertions.assertEquals(responseDTO.user(), loginRequestDTO.user());
    }

    @DisplayName("Login test admin")
    @Test
    void loginAdmin() throws Exception {
        Mockito.when(jwtService.getJWTToken(any(), any())).thenReturn("token");
        var loginRequestDTO = new LoginRequestDTO("adminreing", "password");
        MvcResult mvcResult = client.perform(MockMvcRequestBuilders.post("/v1/security/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(loginRequestDTO))).andReturn();
        LoginResponseDTO responseDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), LoginResponseDTO.class);
        Assertions.assertEquals(mvcResult.getResponse().getStatus(), 200);
        Assertions.assertEquals(responseDTO.token(), "token");
        Assertions.assertEquals(responseDTO.user(), loginRequestDTO.user());
    }
}

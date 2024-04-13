package com.reign.sgonzales.betest.security;

import com.reign.sgonzales.betest.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.aot.DisabledInAotMode;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class JWTFilterTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private JWTService jwtService;
    private JWTFilter jwtFilter;


    @BeforeEach
    void setup() {
        jwtFilter = new JWTFilter(jwtService);
    }

    @DisplayName("Validate Filter valid token")
    @Test
    @DisabledInAotMode
    void testValidateFilter() throws ServletException, IOException {
        Mockito.when(request.getHeader(any())).thenReturn("Bearer dsdsddsd");
        Mockito.when(jwtService.validateAccessToken(any())).thenReturn(true);
        jwtFilter.doFilterInternal(request, response, filterChain);
        Mockito.verify(jwtService).validateAccessToken(any());
    }

    @DisplayName("Validate Filter invalid token")
    @Test
    void testInvalidToken() throws ServletException, IOException {
        Mockito.when(request.getHeader(any())).thenReturn("Bearer dsdsddsd");
        Mockito.when(jwtService.validateAccessToken(any())).thenReturn(false);
        jwtFilter.doFilterInternal(request, response, filterChain);
        Mockito.verify(jwtService).validateAccessToken(any());
    }

    @DisplayName("Validate Filter no tkken")
    @Test
    void testNoToken() throws ServletException, IOException {
        Mockito.when(request.getHeader(any())).thenReturn(StringUtils.EMPTY);
        jwtFilter.doFilterInternal(request, response, filterChain);
        Mockito.verifyNoInteractions(jwtService);
    }

    @DisplayName("Validate Filter no bearer")
    @Test
    void testNotBearer() throws ServletException, IOException {
        Mockito.when(request.getHeader(any())).thenReturn("A");
        jwtFilter.doFilterInternal(request, response, filterChain);
        Mockito.verifyNoInteractions(jwtService);
    }
}

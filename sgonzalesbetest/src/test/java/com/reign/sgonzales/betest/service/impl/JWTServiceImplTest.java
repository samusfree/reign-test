package com.reign.sgonzales.betest.service.impl;

import io.jsonwebtoken.security.WeakKeyException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.reign.sgonzales.betest.utils.CreateObjectsUtils.SECRET_KEY;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class JWTServiceImplTest {
    private JWTServiceImpl jwtService;

    @BeforeEach
    void setup() {
        jwtService = new JWTServiceImpl(SECRET_KEY, 10000);
    }

    @DisplayName("Create token")
    @Test
    void testCreateToken() {
        String token = jwtService.getJWTToken("USER", "USER");
        Assertions.assertNotNull(token);
    }

    @DisplayName("Create token invalid key")
    @Test
    void testInvalidKey() {
        jwtService = new JWTServiceImpl(StringUtils.EMPTY, 10000);
        assertThrows(WeakKeyException.class, () -> jwtService.getJWTToken("USER", "USER"));
    }

    @DisplayName("Validar token")
    @Test
    void testValidateToken() {
        String token = jwtService.getJWTToken("USER", "USER");
        boolean response = jwtService.validateAccessToken(token);
        assertTrue(response);
    }

    @DisplayName("Token invalido")
    @Test
    void testInvalidToken() {
        boolean response = jwtService.validateAccessToken("dsdsdsd");
        assertFalse(response);
    }

    @DisplayName("Token invalido empty")
    @Test
    void testInvalidTokenEmpty() {
        boolean response = jwtService.validateAccessToken(StringUtils.EMPTY);
        assertFalse(response);
    }

    @DisplayName("Token invalido expirado")
    @Test
    void testExpiredToken() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJOaXN1bUpXVCIsInN1YiI6InRlc3RAZ21haWwuY29tIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sIm5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjg3ODQyNjg1LCJleHAiOi0zMjE1NDQ0ODA1NDh9.gnxHTZMMiCehqxD4yvjwK_c_JHyNuIgRRTe5jmM30ZJbN70UCtOk7yLm09ocpBqmlMjv5me-nXsPKj9AOgbgnA";
        boolean response = jwtService.validateAccessToken(token);
        assertFalse(response);
    }

    @DisplayName("Token invalido firmado con otra llave")
    @Test
    void testInvalidSignatureToken() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        boolean response = jwtService.validateAccessToken(token);
        assertFalse(response);
    }

    @DisplayName("Obtener Claims")
    @Test
    void testGetClaims() {
        String user = "USER";
        String role = "USER";
        String token = jwtService.getJWTToken(user, role);
        String response = jwtService.getUserName(token);
        Assertions.assertEquals(user, response);
    }

    @DisplayName("Obtener Role")
    @Test
    void testGetClaimsRole() {
        String user = "USER";
        String role = "USER";
        String token = jwtService.getJWTToken(user, role);
        String response = jwtService.getRole(token);
        Assertions.assertEquals(role, response);
    }

    @DisplayName("Invalid claims")
    @Test
    void testInvalidClaims() {
        assertThrows(IllegalArgumentException.class, () -> jwtService.getUserName(StringUtils.EMPTY));
    }
}

package com.reign.sgonzales.betest.rest;

import com.reign.sgonzales.betest.dto.LoginRequestDTO;
import com.reign.sgonzales.betest.dto.LoginResponseDTO;
import com.reign.sgonzales.betest.service.JWTService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Dummy Security API", description = "Dummy Security API for generate JWT Token")
@RestController
@RequestMapping("/v1/security")
public class SecurityController {
    private final JWTService jwtService;

    @Autowired
    public SecurityController(final JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Operation(summary = "Creates Dummy JWT Token", description = "Creates Dummy JWT Token")
    @PostMapping("/login")
    public LoginResponseDTO Login(@RequestBody LoginRequestDTO loginRequest) {
        String role = loginRequest.user().equals("adminreing") ? "ADMIN" : "USER";
        return new LoginResponseDTO(jwtService.getJWTToken(loginRequest.user(), role), loginRequest.user());
    }
}

package com.reign.sgonzales.betest.rest;

import com.reign.sgonzales.betest.service.IntegrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Integration API", description = "Integration API")
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/v1/integration")
public class IntegrationController {
    private final IntegrationService integrationService;

    public IntegrationController(final IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @Operation(summary = "Populate information in the database", description = "Populate information in the database")
    @Secured("ADMIN")
    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void populate() {
        integrationService.UpdateHackerNewsArticles();
    }
}
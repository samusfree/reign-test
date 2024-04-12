package com.reign.sgonzales.betest.rest;

import com.reign.sgonzales.betest.service.IntegrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/integration")
public class IntegrationController {
    private final IntegrationService integrationService;

    public IntegrationController(final IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @PostMapping
    public void populate() {
        integrationService.UpdateHackerNewsArticles();
    }
}

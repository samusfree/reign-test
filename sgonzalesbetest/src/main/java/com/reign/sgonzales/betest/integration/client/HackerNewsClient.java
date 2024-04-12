package com.reign.sgonzales.betest.integration.client;

import com.reign.sgonzales.betest.integration.dto.HackerNewResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class HackerNewClient {
    @Value("${app.integration.hackernews.url}")
    String urlIntegration;

    public Optional<HackerNewResponse> getInfo() {
        try {
            var response = new RestTemplate().getForObject(urlIntegration, HackerNewResponse.class);
            if (Objects.nonNull(response)) {
                return Optional.of(response);
            }
        } catch (Exception e) {
            log.error("Error when try to obtain data from Hacker news: " + e.getMessage(), e);
        }
        return Optional.empty();
    }
}

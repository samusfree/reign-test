package com.reign.sgonzales.betest.service.impl;

import com.reign.sgonzales.betest.integration.client.HackerNewsClient;
import com.reign.sgonzales.betest.integration.dto.HackerNewResponse;
import com.reign.sgonzales.betest.integration.dto.Hit;
import com.reign.sgonzales.betest.mappers.ArticleMapper;
import com.reign.sgonzales.betest.repository.ArticleRepository;
import com.reign.sgonzales.betest.service.IntegrationService;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Optional;

public class IntegrationServiceImpl implements IntegrationService {
    private final HackerNewsClient hackerNewsClient;
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public IntegrationServiceImpl(final HackerNewsClient hackerNewsClient, final ArticleRepository articleRepository,
            final ArticleMapper articleMapper) {
        this.hackerNewsClient = hackerNewsClient;
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    @Scheduled(fixedRateString = "${app.integration.schedule.fixedrate}")
    public void UpdateHackerNewsArticles() {
        Optional<HackerNewResponse> response = hackerNewsClient.getInfo();
        if (response.isPresent() && !response.get().hits().isEmpty()) {
            saveArticles(response.get().hits());
        }
    }

    private void saveArticles(List<Hit> hits) {
        hits.forEach(h -> articleRepository.save(articleMapper.fromHit(h)));
    }
}

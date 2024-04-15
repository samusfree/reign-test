package com.reign.sgonzales.betest.service.impl;

import com.reign.sgonzales.betest.data.repository.ArticleRepository;
import com.reign.sgonzales.betest.integration.client.HackerNewsClient;
import com.reign.sgonzales.betest.integration.dto.HackerNewResponse;
import com.reign.sgonzales.betest.mappers.ArticleMapper;
import com.reign.sgonzales.betest.service.IntegrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static com.reign.sgonzales.betest.utils.CreateObjectsUtils.getResponse;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class IntegrationServiceTest {
    @Mock
    private HackerNewsClient hackerNewsClient;
    @Mock
    private ArticleRepository articleRepository;
    private IntegrationService integrationService;

    @BeforeEach
    void setup() {
        integrationService = new IntegrationServiceImpl(hackerNewsClient, articleRepository, new ArticleMapper());
    }
    @DisplayName("Get one article from API")
    @Test
    void testOneArticleFromAPI() {
        Mockito.when(hackerNewsClient.getInfo()).thenReturn(Optional.of(getResponse()));
        integrationService.UpdateHackerNewsArticles();
        Mockito.verify(articleRepository, Mockito.times(1)).save(any());
    }

    @DisplayName("No response from the API")
    @Test
    void testEmptyResponseAPI() {
        Mockito.when(hackerNewsClient.getInfo()).thenReturn(Optional.empty());
        integrationService.UpdateHackerNewsArticles();
        Mockito.verify(articleRepository, Mockito.times(0)).save(any());
    }

    @DisplayName("No articles from the API")
    @Test
    void testEmptyHitsFromAPI() {
        Mockito.when(hackerNewsClient.getInfo()).thenReturn(Optional.of(new HackerNewResponse(new ArrayList<>())));
        integrationService.UpdateHackerNewsArticles();
        Mockito.verify(articleRepository, Mockito.times(0)).save(any());
    }
}

package com.reign.sgonzales.betest.service.impl;

import com.reign.sgonzales.betest.data.impl.ArticleDAOImpl;
import com.reign.sgonzales.betest.data.repository.ArticleRepository;
import com.reign.sgonzales.betest.entities.Article;
import com.reign.sgonzales.betest.mappers.ArticleMapper;
import com.reign.sgonzales.betest.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static com.reign.sgonzales.betest.utils.CreateObjectsUtils.getArticle;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceImplTest {
    @Mock
    private ArticleRepository articleRepository;
    private ArticleService articleService;

    @BeforeEach
    void setup() {
        var articleDAO = new ArticleDAOImpl(articleRepository);
        articleService = new ArticleServiceImpl(articleDAO, new ArticleMapper());
    }

    @DisplayName("Delete an Article")
    @Test
    void deleteAnArticle() {
        articleService.removeArticle("1");
        Mockito.verify(articleRepository, Mockito.times(1)).findById(any());
    }

    @DisplayName("Find an Article")
    @Test
    void findArticle() {
        Mockito.when(articleRepository.findAll(any(Query.class), any(Pageable.class))).thenReturn(new PageImpl<Article>(
                List.of(getArticle())));
        articleService.find(1, 5, "a", null, null, null);
        Mockito.verify(articleRepository, Mockito.times(1)).findAll(any(Query.class), any(Pageable.class));
    }
}

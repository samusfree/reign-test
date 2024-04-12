package com.reign.sgonzales.betest.service.impl;

import com.reign.sgonzales.betest.dto.ArticlePaginatedResponse;
import com.reign.sgonzales.betest.entities.Article;
import com.reign.sgonzales.betest.enums.MonthWordEnum;
import com.reign.sgonzales.betest.mappers.ArticleMapper;
import com.reign.sgonzales.betest.repository.ArticleRepository;
import com.reign.sgonzales.betest.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImpl(final ArticleRepository articleRepository, final ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public ArticlePaginatedResponse find(Integer page, Integer size, String author, String tag, String title,
            MonthWordEnum month) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Article> pageobject = articleRepository.findArticleByAuthorContainingIgnoreCase(author, pageable);
        return ArticlePaginatedResponse.builder().pages(pageobject.getTotalPages()).currentPage(page).size(size)
                .count(pageobject.getTotalElements()).data(pageobject.map(articleMapper::fromArticle).toList()).build();
    }

    @Override
    public void removeArticle(String objectId) {
        Optional<Article> article = articleRepository.findById(objectId);
        article.ifPresent(articleRepository::delete);
    }
}

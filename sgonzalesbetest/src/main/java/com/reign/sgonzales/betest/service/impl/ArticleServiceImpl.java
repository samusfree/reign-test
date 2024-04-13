package com.reign.sgonzales.betest.service.impl;

import com.reign.sgonzales.betest.data.ArticleDAO;
import com.reign.sgonzales.betest.dto.ArticlePaginatedResponseDTO;
import com.reign.sgonzales.betest.entities.Article;
import com.reign.sgonzales.betest.enums.MonthWordEnum;
import com.reign.sgonzales.betest.mappers.ArticleMapper;
import com.reign.sgonzales.betest.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleDAO articleDAO;
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImpl(final ArticleDAO articleDAO, final ArticleMapper articleMapper) {
        this.articleDAO = articleDAO;
        this.articleMapper = articleMapper;
    }

    @Override
    public ArticlePaginatedResponseDTO find(Integer page, Integer size, String author, String tag, String title,
            MonthWordEnum month) {
        Page<Article> pageobject = articleDAO.find(page, size, author, tag, title, month);
        return ArticlePaginatedResponseDTO.builder().pages(pageobject.getTotalPages()).currentPage(page).size(size)
                .count(pageobject.getTotalElements()).data(pageobject.map(articleMapper::fromArticle).toList()).build();
    }

    @Override
    public void removeArticle(String objectId) {
        articleDAO.deleteByObjectId(objectId);
    }
}

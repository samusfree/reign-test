package com.reign.sgonzales.betest.service;

import com.reign.sgonzales.betest.dto.ArticlePaginatedResponseDTO;
import com.reign.sgonzales.betest.enums.MonthWordEnum;

public interface ArticleService {
    ArticlePaginatedResponseDTO find(Integer page, Integer size, String author, String tag, String title,
            MonthWordEnum month);

    void removeArticle(String objectId);
}

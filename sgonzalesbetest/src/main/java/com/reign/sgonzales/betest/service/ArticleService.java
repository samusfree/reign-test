package com.reign.sgonzales.betest.service;

import com.reign.sgonzales.betest.dto.ArticlePaginatedResponse;
import com.reign.sgonzales.betest.enums.MonthWordEnum;

public interface ArticleService {
    ArticlePaginatedResponse find(Integer page, Integer size, String author, String tag, String title,
            MonthWordEnum month);

    void removeArticle(String objectId);
}

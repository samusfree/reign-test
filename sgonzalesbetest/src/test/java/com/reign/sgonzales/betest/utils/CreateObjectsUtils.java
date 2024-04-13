package com.reign.sgonzales.betest.utils;

import com.reign.sgonzales.betest.dto.ArticleDTO;
import com.reign.sgonzales.betest.entities.Article;
import com.reign.sgonzales.betest.integration.dto.Hit;

import java.util.Date;

public class CreateObjectsUtils {

    public static Hit getHit() {
        return new Hit("1", null, "author", "", new Date(), new Date().getTime(), "title", "url", new Date());
    }
    public static Article getArticle() {
        return Article.builder().objectId("1").createAt(new Date()).createdAtTimestamp(new Date().getTime()).url("url").author("author").build();
    }

    public static ArticleDTO getArticleDTO() {
        return ArticleDTO.builder().objectId("1").url("url").author("author").build();
    }
}

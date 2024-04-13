package com.reign.sgonzales.betest.mappers;

import com.reign.sgonzales.betest.utils.CreateObjectsUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArticleMapperTest {
    private ArticleMapper articleMapper;

    @BeforeEach
    public void config() {
        articleMapper = new ArticleMapper();
    }

    @DisplayName("Validate fromHit")
    @Test
    public void validateFromHit() {
        var hit = CreateObjectsUtils.getHit();
        var expectedArticle = CreateObjectsUtils.getArticle();
        var response =  articleMapper.fromHit(hit);

        Assertions.assertEquals(expectedArticle.getObjectId(), response.getObjectId());
        Assertions.assertEquals(expectedArticle.getAuthor(), response.getAuthor());
        Assertions.assertEquals(expectedArticle.getUrl(), response.getUrl());
    }

    @DisplayName("Validate fromArticle")
    @Test
    public void fromArticle() {
        var article = CreateObjectsUtils.getArticle();
        var expectedDTO = CreateObjectsUtils.getArticleDTO();
        var response =  articleMapper.fromArticle(article);

        Assertions.assertEquals(expectedDTO.getObjectId(), response.getObjectId());
        Assertions.assertEquals(expectedDTO.getAuthor(), response.getAuthor());
        Assertions.assertEquals(expectedDTO.getUrl(), response.getUrl());
    }
}

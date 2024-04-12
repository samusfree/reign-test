package com.reign.sgonzales.betest.mappers;

import com.reign.sgonzales.betest.dto.ArticleDTO;
import com.reign.sgonzales.betest.entities.Article;
import com.reign.sgonzales.betest.integration.dto.Hit;
import org.springframework.stereotype.Component;

@Component("articleMapper")
public class ArticleMapper {
    public Article fromHit(Hit hit) {
        return Article.builder().author(hit.author()).text(hit.text()).url(hit.url()).tags(hit.tags())
                .title(hit.title()).createAt(hit.createAt()).createdAtTimestamp(hit.createdAtTimestamp())
                .objectId(hit.objectId()).updatedAt(hit.updatedAt()).build();
    }

    public ArticleDTO fromArticle(Article article) {
        return ArticleDTO.builder().author(article.getAuthor()).tags(article.getTags()).url(article.getUrl())
                .text(article.getText()).objectId(article.getObjectId()).title(article.getTitle()).build();
    }
}

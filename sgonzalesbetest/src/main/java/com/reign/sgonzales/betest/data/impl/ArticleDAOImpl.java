package com.reign.sgonzales.betest.data.impl;

import com.reign.sgonzales.betest.data.ArticleDAO;
import com.reign.sgonzales.betest.data.repository.ArticleRepository;
import com.reign.sgonzales.betest.entities.Article;
import com.reign.sgonzales.betest.enums.MonthWordEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class ArticleDAOImpl implements ArticleDAO {
    private final ArticleRepository articleRepository;

    public ArticleDAOImpl(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void deleteByObjectId(String objectId) {
        Optional<Article> article = articleRepository.findById(objectId);
        article.ifPresent(articleRepository::delete);
    }

    @Override
    public Page<Article> find(Integer page, Integer size, String author, String tag, String title,
            MonthWordEnum month) {
        Query dynamicQuery = new Query();

        if(Objects.nonNull(author) && !author.isEmpty()) {
            Criteria authorCriteria = Criteria.where("author").regex(String.format(".*%s.*", author), "i");
            dynamicQuery.addCriteria(authorCriteria);
        }
        if(Objects.nonNull(title) && !title.isEmpty()) {
            Criteria authorCriteria = Criteria.where("title").regex(String.format(".*%s.*", title), "i");
            dynamicQuery.addCriteria(authorCriteria);
        }
        if(Objects.nonNull(tag) && !tag.isEmpty()) {
            Criteria authorCriteria = Criteria.where("tags").regex(String.format(".*%s.*", tag), "i");
            dynamicQuery.addCriteria(authorCriteria);
        }
        if(Objects.nonNull(month)) {
            int monthInt = month.ordinal() + 1;
            Criteria monthCriteria = Criteria.expr(MongoExpression.create("{ \"$eq\": [{ \"$month\": \"$createAt\" }, " + monthInt + "] }"));
            dynamicQuery.addCriteria(monthCriteria);
        }

        return articleRepository.findAll(dynamicQuery,PageRequest.of(page -1, size));
    }
}

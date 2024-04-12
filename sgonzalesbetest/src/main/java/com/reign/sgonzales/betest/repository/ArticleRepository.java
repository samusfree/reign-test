package com.reign.sgonzales.betest.repository;

import com.reign.sgonzales.betest.entities.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("articleRepository")
public interface ArticleRepository extends MongoRepository<Article, String> {
}

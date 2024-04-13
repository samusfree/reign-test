package com.reign.sgonzales.betest.data.repository;

import com.reign.sgonzales.betest.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("articleRepository")
public interface ArticleRepository extends ResourceRepository<Article, String> {
}

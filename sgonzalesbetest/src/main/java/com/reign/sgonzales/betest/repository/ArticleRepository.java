package com.reign.sgonzales.betest.repository;

import com.reign.sgonzales.betest.entities.Article;
import com.reign.sgonzales.betest.enums.MonthWordEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("articleRepository")
public interface ArticleRepository extends MongoRepository<Article, String> {
    Page<Article> findArticleByAuthorContainingIgnoreCase(String author, Pageable pageable);
    Page<Article> findArticleByTagsContaining(String tag, Pageable pageable);
}

package com.reign.sgonzales.betest.data;

import com.reign.sgonzales.betest.entities.Article;
import com.reign.sgonzales.betest.enums.MonthWordEnum;
import org.springframework.data.domain.Page;

public interface ArticleDAO {
    void  deleteByObjectId(String objectId);
    Page<Article> find(Integer page, Integer size, String author, String tag, String title,
            MonthWordEnum month);

}

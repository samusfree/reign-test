package com.reign.sgonzales.betest.rest;

import com.reign.sgonzales.betest.dto.ArticlePaginatedResponse;
import com.reign.sgonzales.betest.enums.MonthWordEnum;
import com.reign.sgonzales.betest.service.ArticleService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/articles")
public class ArticlesController {
    private final ArticleService articleService;

    public ArticlesController(final ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public ArticlePaginatedResponse listArticles(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "month", required = false) MonthWordEnum month) {
        return articleService.find(page >= 1 ? page : 1, size, author, tag, title, month);
    }

    @DeleteMapping("/{objectID}")
    public void removeArticle(@PathVariable("objectID") String objectId) {
        articleService.removeArticle(objectId);
    }
}

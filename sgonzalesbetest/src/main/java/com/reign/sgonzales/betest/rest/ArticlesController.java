package com.reign.sgonzales.betest.rest;

import com.reign.sgonzales.betest.dto.ArticlePaginatedResponseDTO;
import com.reign.sgonzales.betest.enums.MonthWordEnum;
import com.reign.sgonzales.betest.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Article API", description = "Article API")
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/v1/articles")
public class ArticlesController {
    private final ArticleService articleService;

    public ArticlesController(final ArticleService articleService) {
        this.articleService = articleService;
    }

    @Operation(summary = "Find Articles by author, tag, title, month", description = "Find Articles by author, tag, title, month")
    @Secured({ "USER", "ADMIN" })
    @GetMapping("/")
    public ArticlePaginatedResponseDTO listArticles(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "month", required = false) MonthWordEnum month) {
        return articleService.find(page >= 1 ? page : 1, size, author, tag, title, month);
    }

    @Operation(summary = "Delete an Article by objectId", description = "Delete an Article by objectId")
    @Secured("ADMIN")
    @DeleteMapping("/{objectID}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeArticle(@PathVariable("objectID") String objectId) {
        articleService.removeArticle(objectId);
    }
}

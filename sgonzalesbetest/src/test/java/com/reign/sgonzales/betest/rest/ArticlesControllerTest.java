package com.reign.sgonzales.betest.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.reign.sgonzales.betest.dto.ArticlePaginatedResponseDTO;
import com.reign.sgonzales.betest.dto.LoginResponseDTO;
import com.reign.sgonzales.betest.service.ArticleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.reign.sgonzales.betest.utils.CreateObjectsUtils.getArticleDTO;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ArticlesControllerTest {
    @Mock
    private ArticleService articleService;
    private MockMvc client;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = JsonMapper.builder()
                .findAndAddModules()
                .build();
        var articlesController = new ArticlesController(articleService);
        client = MockMvcBuilders.standaloneSetup(articlesController).build();
    }

    @DisplayName("remove an article")
    @Test
    public void testIntegration() throws Exception {
        MvcResult mvcResult = client.perform(
                        MockMvcRequestBuilders.delete("/v1/articles/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        Assertions.assertEquals(mvcResult.getResponse().getStatus(), 204);
    }

    @DisplayName("find Articles")
    @Test
    void findArticles() throws Exception {
        var response = ArticlePaginatedResponseDTO.builder().size(1).count(1).pages(1).currentPage(1)
                .data(List.of(getArticleDTO())).build();
        Mockito.when(articleService.find(any(), any(), any(), any(), any(), any())).thenReturn(response);
        MvcResult mvcResult = client.perform(
                MockMvcRequestBuilders.get("/v1/articles/?page=1&size=1&author=author").contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        var responseDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                ArticlePaginatedResponseDTO.class);
        Assertions.assertEquals(mvcResult.getResponse().getStatus(), 200);
        Assertions.assertEquals(responseDTO.getCount(), response.getCount());
        Assertions.assertEquals(responseDTO.getData().getFirst().getAuthor(), response.getData().getFirst().getAuthor());
    }

    @DisplayName("find Articles")
    @Test
    void findArticlesPageLowerThanOne() throws Exception {
        var response = ArticlePaginatedResponseDTO.builder().size(1).count(1).pages(2).currentPage(2)
                .data(List.of(getArticleDTO())).build();
        Mockito.when(articleService.find(any(), any(), any(), any(), any(), any())).thenReturn(response);
        MvcResult mvcResult = client.perform(
                MockMvcRequestBuilders.get("/v1/articles/?page=-1&size=1&author=author").contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        var responseDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                ArticlePaginatedResponseDTO.class);
        Assertions.assertEquals(mvcResult.getResponse().getStatus(), 200);
        Assertions.assertEquals(responseDTO.getCount(), response.getCount());
        Assertions.assertEquals(responseDTO.getData().getFirst().getAuthor(), response.getData().getFirst().getAuthor());
    }
}

package com.reign.sgonzales.betest.data;

import com.reign.sgonzales.betest.data.impl.ArticleDAOImpl;
import com.reign.sgonzales.betest.data.repository.ArticleRepository;
import com.reign.sgonzales.betest.entities.Article;
import com.reign.sgonzales.betest.enums.MonthWordEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ArticleDAOImplTest {
    @Mock
    private ArticleRepository articleRepository;
    private ArticleDAO articleDAO;

    @BeforeEach
    void setup() {
        articleDAO = new ArticleDAOImpl(articleRepository);
    }

    @DisplayName("Test delete")
    @Test
    public void testDelete() {
        String obiectId = "1";
        Mockito.when(articleRepository.findById(obiectId)).thenReturn(Optional.of(new Article()));
        articleDAO.deleteByObjectId(obiectId);
        Mockito.verify(articleRepository).findById(any());
        Mockito.verify(articleRepository).delete(any());
    }

    @DisplayName("Test delete no exists")
    @Test
    public void testDeleteNoExists() {
        String obiectId = "1";
        Mockito.when(articleRepository.findById(obiectId)).thenReturn(Optional.empty());
        articleDAO.deleteByObjectId(obiectId);
        Mockito.verify(articleRepository, Mockito.times(0)).delete(any());
        Mockito.verify(articleRepository).findById(any());
    }

    @DisplayName("Test find")
    @Test
    public void find() {
        articleDAO.find(1, 5, "a", "t", "t", MonthWordEnum.april);
    }

    @DisplayName("Test find only author")
    @Test
    public void findOnlyAuthor() {
        articleDAO.find(1, 5, "a", null, null, null);
    }

    @DisplayName("Test find only empty author")
    @Test
    public void findOnlyEmptyAuthor() {
        articleDAO.find(1, 5, "", null, null, null);
    }

    @DisplayName("Test find only tag")
    @Test
    public void findOnlyTag() {
        articleDAO.find(1, 5, null, "a", null, null);
    }

    @DisplayName("Test find only empty tag")
    @Test
    public void findOnlyEmptyTag() {
        articleDAO.find(1, 5, null, "", null, null);
    }

    @DisplayName("Test find only title")
    @Test
    public void findOnlyTitle() {
        articleDAO.find(1, 5, null, null, "a", null);
    }

    @DisplayName("Test find only empty title")
    @Test
    public void findOnlyEmptyTitle() {
        articleDAO.find(1, 5, null, null, "", null);
    }

    @DisplayName("Test find only month")
    @Test
    public void findOnlyMonth() {
        articleDAO.find(1, 5, null, null, null, MonthWordEnum.april);
    }
}
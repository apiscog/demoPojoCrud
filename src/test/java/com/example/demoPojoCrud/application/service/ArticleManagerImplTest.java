package com.example.demoPojoCrud.application.service;

import com.example.demoPojoCrud.domain.model.Article;
import com.example.demoPojoCrud.domain.ports.out.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ArticleManagerImplTest {

    private ArticleManagerImpl articleManager;
    @Mock
    private ArticleRepository articleRepository;

    @BeforeEach
    public void setUp() {
        articleManager = new ArticleManagerImpl(articleRepository);
    }

    @Test
    public void createArticle() {
        Article article = new Article();
        article.setDescription("Test Article");
        article.setWeight(1.9);
        article.setVolume(1.8);
        article.setActive(true);

        Article savedArticle = new Article();
        savedArticle.setId(1L);
        savedArticle.setDescription("Article to create");
        savedArticle.setWeight(1.8);
        savedArticle.setVolume(1.9);
        savedArticle.setActive(true);

        when(articleRepository.save(any(Article.class))).thenReturn(savedArticle);

        Article createdArticle = articleManager.createArticle(article);
        assertNotNull(createdArticle.getId(), "The article ID should not be null");
        assertEquals("Article to create", createdArticle.getDescription());
    }

    @Test
    public void updateArticle() {
        Article existingArticle = new Article();
        existingArticle.setId(1L);
        existingArticle.setDescription("Test Article");
        existingArticle.setWeight(1.0);
        existingArticle.setVolume(1.0);
        existingArticle.setActive(true);

        when(articleRepository.findById(1L)).thenReturn(Optional.of(existingArticle));

        Article updatedArticle = new Article();
        updatedArticle.setId(1L);
        updatedArticle.setDescription("Article to update");
        updatedArticle.setWeight(1.5);
        updatedArticle.setVolume(1.6);
        updatedArticle.setActive(true);

        when(articleRepository.save(any(Article.class))).thenReturn(updatedArticle);

        Optional<Article> result = articleManager.updateArticle(1L, updatedArticle);
        assertTrue(result.isPresent());
        assertEquals("Article to update", result.get().getDescription());
    }

    @Test
    public void deleteArticle() {
        Article article = new Article();
        article.setId(1L);

        when(articleRepository.deleteById(1L)).thenReturn(true);

        boolean isDeleted = articleManager.deleteArticle(1L);

        assertTrue(isDeleted);
    }
}

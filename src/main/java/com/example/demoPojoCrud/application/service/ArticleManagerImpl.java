package com.example.demoPojoCrud.application.service;

import com.example.demoPojoCrud.domain.model.Article;
import com.example.demoPojoCrud.domain.ports.in.ArticleManager;
import com.example.demoPojoCrud.domain.ports.out.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleManagerImpl implements ArticleManager {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleManagerImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Optional<Article> updateArticle(Long id, Article article) {
        if (articleRepository.findById(id).isPresent()) {
            article.setId(id);
            return Optional.of(articleRepository.save(article));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteArticle(Long id) {
        return articleRepository.deleteById(id);
    }
}

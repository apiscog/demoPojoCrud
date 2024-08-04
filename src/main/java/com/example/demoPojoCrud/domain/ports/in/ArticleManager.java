package com.example.demoPojoCrud.domain.ports.in;

import com.example.demoPojoCrud.domain.model.Article;

import java.util.Optional;

public interface ArticleManager {
    Article createArticle(Article article);

    Optional<Article> updateArticle(Long id, Article article);

    boolean deleteArticle(Long id);
}

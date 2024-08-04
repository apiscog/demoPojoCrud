package com.example.demoPojoCrud.domain.ports.out;

import com.example.demoPojoCrud.domain.model.Article;

import java.util.Optional;

public interface ArticleRepository {
    Article save(Article article);

    Optional<Article> findById(Long id);

    boolean deleteById(Long id);
}

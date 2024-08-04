package com.example.demoPojoCrud.adapters.out.persistance;

import com.example.demoPojoCrud.domain.model.Article;
import com.example.demoPojoCrud.domain.ports.out.ArticleRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryRepository implements ArticleRepository {

    private final Map<Long, Article> articles = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public Article save(Article article) {
        if (article.getId() == null) {
            article.setId(currentId++);
        }
        articles.put(article.getId(), article);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(articles.get(id));
    }

    @Override
    public boolean deleteById(Long id) {
        return articles.remove(id) != null;
    }
}

package com.example.demoPojoCrud.adapters.in.web;

import com.example.demoPojoCrud.domain.model.Article;
import com.example.demoPojoCrud.domain.ports.in.ArticleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleManager articleManager;

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        try {
            Article createdArticle = articleManager.createArticle(article);
            LOG.info("Article created successfully with ID: {}", createdArticle.getId());
            return ResponseEntity.ok(createdArticle);
        } catch (Exception e) {
            LOG.error("Failed to create article", e);
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        try {
            Optional<Article> updatedArticle = articleManager.updateArticle(id, article);
            if (updatedArticle.isPresent()) {
                LOG.info("Article updated successfully with ID: {}", id);
                return ResponseEntity.ok(updatedArticle.get());
            } else {
                LOG.warn("Article with ID: {} not found", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            LOG.error("Failed to update article with ID: {}", id, e);
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        try {
            boolean deleted = articleManager.deleteArticle(id);
            if (deleted) {
                LOG.info("Article deleted successfully with ID: {}", id);
                return ResponseEntity.ok().build();
            } else {
                LOG.warn("Article with ID: {} not found", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            LOG.error("Failed to delete article with ID: {}", id, e);
            return ResponseEntity.status(500).build();
        }
    }
}

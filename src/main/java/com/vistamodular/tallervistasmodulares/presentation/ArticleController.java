package com.vistamodular.tallervistasmodulares.presentation;

import com.vistamodular.tallervistasmodulares.business.ArticleService;
import com.vistamodular.tallervistasmodulares.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de artículos.
 * Implementa el patrón MVC como controlador.
 */
@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    
    @Autowired
    private ArticleService articleService;
    
    /**
     * Endpoint para enviar un nuevo artículo.
     * 
     * @param article El artículo a guardar
     * @return ResponseEntity con el artículo guardado o error si falta el email
     */
    @PostMapping
    public ResponseEntity<Article> submitArticle(@RequestBody Article article) {
        if (article.getAuthorEmail() == null || article.getAuthorEmail().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        Article savedArticle = articleService.submitArticle(article);
        return ResponseEntity.ok(savedArticle);
    }
    
    /**
     * Endpoint para obtener todos los artículos.
     * 
     * @return Lista de todos los artículos almacenados
     */
    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }
}

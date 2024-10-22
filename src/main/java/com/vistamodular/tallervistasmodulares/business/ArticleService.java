package com.vistamodular.tallervistasmodulares.business;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vistamodular.tallervistasmodulares.domain.Article;
import com.vistamodular.tallervistasmodulares.persistence.ArticleRepository;
import com.vistamodular.tallervistasmodulares.common.EmailPlugin;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private PluginManager pluginManager;
    
    private final List<Consumer<Article>> observers = new ArrayList<>();

    public void addObserver(Consumer<Article> observer) {
        observers.add(observer);
    }

    @Transactional
    public Article submitArticle(Article article) {
        Article savedArticle = articleRepository.save(article);
        notifyObservers(savedArticle);
        sendNotificationEmail(savedArticle);
        return savedArticle;
    }

    private void notifyObservers(Article article) {
        for (Consumer<Article> observer : observers) {
            observer.accept(article);
        }
    }

    private void sendNotificationEmail(Article article) {
        if (article.getAuthorEmail() == null || article.getAuthorEmail().isEmpty()) {
            System.out.println("No se pudo enviar el correo: la dirección de correo del autor es nula o vacía.");
            return;
        }
        EmailPlugin emailPlugin = pluginManager.getPlugin("simplemail");
        if (emailPlugin != null) {
            String subject = "Artículo Recibido: " + article.getTitle();
            String body = "Su artículo '" + article.getTitle() + "' ha sido recibido correctamente.";
            emailPlugin.sendEmail(article.getAuthorEmail(), subject, body);
        } else {
            System.out.println("No se pudo enviar el correo: plugin de correo no encontrado.");
        }
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
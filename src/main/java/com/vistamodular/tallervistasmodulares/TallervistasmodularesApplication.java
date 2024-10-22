package com.vistamodular.tallervistasmodulares;

import javax.swing.SwingUtilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; // Añade este import
import org.springframework.context.ConfigurableApplicationContext;

import com.vistamodular.tallervistasmodulares.business.ArticleService;
import com.vistamodular.tallervistasmodulares.presentation.ArticleListView;

/**
 * Clase principal de la aplicación que configura Spring Boot y la interfaz gráfica.
 * Implementa el patrón MVC para la gestión de artículos científicos.
 */
@SpringBootApplication
public class TallervistasmodularesApplication {

    /**
     * Punto de entrada principal de la aplicación.
     * Configura el entorno Spring Boot y la interfaz gráfica Swing.
     *
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Habilita el modo gráfico para Swing
        System.setProperty("java.awt.headless", "false");
        
        // Inicia el contexto de Spring Boot
        ConfigurableApplicationContext context = SpringApplication.run(TallervistasmodularesApplication.class, args);
        
        // Configura la interfaz gráfica en el Event Dispatch Thread de Swing
        SwingUtilities.invokeLater(() -> {
            // Crea la vista de la lista de artículos
            ArticleListView view = new ArticleListView();
            // Obtiene el servicio de artículos del contexto de Spring
            ArticleService articleService = context.getBean(ArticleService.class);
            // Registra la vista como observador del servicio (patrón Observer)
            articleService.addObserver(article -> view.update(article));
        });
    }
}

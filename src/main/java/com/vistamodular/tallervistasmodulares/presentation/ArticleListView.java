package com.vistamodular.tallervistasmodulares.presentation;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.vistamodular.tallervistasmodulares.domain.Article;


/**
 * Vista Swing para mostrar la lista de artículos.
 * Implementa el patrón MVC como vista y el patrón Observer para actualizaciones.
 */
public class ArticleListView {
    private final JFrame frame;
    private final DefaultListModel<String> listModel;

    /**
     * Constructor que inicializa la interfaz gráfica.
     * Configura la ventana principal y la lista de artículos.
     */
    public ArticleListView() {
        // Inicialización del modelo de la lista
        listModel = new DefaultListModel<>();
        
        // Configuración de la ventana principal
        frame = new JFrame("Lista de Artículos");
        JList<String> list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        
        // Configuración del layout y propiedades de la ventana
        frame.getContentPane().add(scrollPane);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Método de actualización llamado cuando se recibe un nuevo artículo.
     * Implementa la parte del observador del patrón Observer.
     *
     * @param article El artículo nuevo a mostrar en la lista
     */
    public void update(Article article) {
        SwingUtilities.invokeLater(() -> {
            String articleInfo = String.format("%s - %s (%s)", 
                article.getTitle(), 
                article.getAuthorName(), 
                article.getAuthorEmail());
            listModel.addElement(articleInfo);
        });
    }
}
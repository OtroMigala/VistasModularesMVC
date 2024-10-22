package com.vistamodular.tallervistasmodulares.business;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vistamodular.tallervistasmodulares.common.EmailPlugin;
import com.vistamodular.tallervistasmodulares.plugins.SimpleMailPlugin;

import jakarta.annotation.PostConstruct; // Cambia esta importación

@Component
public class PluginManager {
    private final Map<String, EmailPlugin> plugins = new HashMap<>();
    
    @Autowired
    private SimpleMailPlugin simpleMailPlugin;

    @PostConstruct
    public void init() {
        // Registra el plugin de correo por defecto después de que Spring haya inyectado las dependencias
        registerPlugin("simplemail", simpleMailPlugin);
    }

    public void registerPlugin(String name, EmailPlugin plugin) {
        plugins.put(name, plugin);
    }

    public EmailPlugin getPlugin(String name) {
        return plugins.get(name);
    }
}
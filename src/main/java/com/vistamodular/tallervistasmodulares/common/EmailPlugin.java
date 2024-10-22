package com.vistamodular.tallervistasmodulares.common;

public interface EmailPlugin {
    void sendEmail(String to, String subject, String body);
}
package com.vistamodular.tallervistasmodulares.plugins;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.vistamodular.tallervistasmodulares.common.EmailPlugin;

/**
 * Plugin de correo electrónico que implementa la interfaz EmailPlugin utilizando Simple Java Mail.
 * Parte del patrón Microkernel para el manejo modular de envío de correos.
 */
@Component
public class SimpleMailPlugin implements EmailPlugin {
    
    // Inyección de configuración desde application.properties
    @Value("${simplejavamail.smtp.host}")
    private String smtpHost;
    
    @Value("${simplejavamail.smtp.port}")
    private Integer smtpPort;
    
    @Value("${simplejavamail.smtp.username}")
    private String username;
    
    @Value("${simplejavamail.smtp.password}")
    private String password;

    /**
     * Implementación del método de envío de correos utilizando Simple Java Mail.
     *
     * @param to Dirección de correo del destinatario
     * @param subject Asunto del correo
     * @param body Contenido del correo
     */
    @Override
    public void sendEmail(String to, String subject, String body) {
        try {
            // Construye el email usando el patrón Builder
            Email email = EmailBuilder.startingBlank()
                .from("Sistema de Conferencias", username)
                .to(to)
                .withSubject(subject)
                .withPlainText(body)
                .buildEmail();

            // Configura el mailer con los datos del servidor SMTP
            Mailer mailer = MailerBuilder
                .withSMTPServer(smtpHost, smtpPort, username, password)
                .withTransportStrategy(org.simplejavamail.api.mailer.config.TransportStrategy.SMTP_TLS)
                .buildMailer();

            // Envía el correo
            mailer.sendMail(email);
            System.out.println("Correo enviado exitosamente a: " + to);
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
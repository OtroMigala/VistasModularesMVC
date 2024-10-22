# Sistema de Gestión de Artículos Científicos (CAIA)

## Descripción
Este proyecto implementa un sistema de gestión de artículos científicos que permite a los usuarios enviar artículos y notifica automáticamente a los autores mediante correo electrónico. El sistema está construido siguiendo los patrones arquitectónicos MVC y Microkernel.

## Características Principales
- Interfaz gráfica para visualizar artículos recibidos en tiempo real
- API REST para la gestión de artículos
- Sistema de notificación por correo electrónico usando el patrón Microkernel
- Persistencia de datos usando JPA/Hibernate
- Implementación de patrones de diseño (MVC, Observer, Microkernel)

## Tecnologías Utilizadas
- Java 17
- Spring Boot 3.3.4
- Simple Java Mail 8.12.2
- Swing (para la interfaz gráfica)
- H2 Database (base de datos en memoria)
- Maven

## Estructura del Proyecto

src/
├── main/
│   ├── java/
│   │   └── com.vistamodular.tallervistasmodulares/
│   │       ├── business/
│   │       │   ├── ArticleService.java
│   │       │   └── PluginManager.java
│   │       ├── common/
│   │       │   ├── ArticleObserver.java
│   │       │   └── EmailPlugin.java
│   │       ├── domain/
│   │       │   └── Article.java
│   │       ├── persistence/
│   │       │   └── ArticleRepository.java
│   │       ├── plugins/
│   │       │   └── SimpleMailPlugin.java
│   │       ├── presentation/
│   │       │   ├── ArticleController.java
│   │       │   └── ArticleListView.java
│   │       └── TallervistasmodularesApplication.java
│   └── resources/
│       └── application.properties

## Configuración

### Requisitos Previos
- JDK 17 o superior
- Maven 3.6 o superior
- Un cliente de correo configurado (Gmail recomendado)

### Configuración del Correo Electrónico
Crear un archivo `application.properties` en `src/main/resources` con la siguiente configuración:

```properties
# Configuración básica
spring.application.name=tallervistasmodulares
spring.datasource.url=jdbc:h2:mem:conferencedb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true

# Configuración de Simple Java Mail
simplejavamail.smtp.host=smtp.gmail.com
simplejavamail.smtp.port=587
simplejavamail.smtp.username=tu_correo@gmail.com
simplejavamail.smtp.password=tu_contraseña_de_aplicacion

# Configuración de la interfaz gráfica
spring.main.web-application-type=servlet
spring.main.allow-bean-definition-overriding=true


Uso
API REST
Enviar un Nuevo Artículo
httpCopyPOST http://localhost:8080/api/articles
Content-Type: application/json

{
    "title": "Título del Artículo",
    "authorName": "Nombre del Autor",
    "authorEmail": "autor@ejemplo.com"
}
Obtener Todos los Artículos
httpCopyGET http://localhost:8080/api/articles

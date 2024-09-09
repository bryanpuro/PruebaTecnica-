Prueba Técnica
El proyecto está desarrollado en Java y utiliza el framework Spring Boot en su versión 2.5.10, construido con Gradle y usando Java 8. Como gestor de base de datos, se utiliza PostgreSQL. 
El script de la base de datos se encuentra en la ruta \src\main\Files, y la base de datos se llama "prueba". 
Las credenciales de acceso son: usuario postgres y contraseña root. El proyecto se ejecuta localmente en el puerto 8080 y no cuenta con medidas de seguridad para acceder a los servicios web.

La estructura del proyecto está organizada de la siguiente manera:
Cliente
    *Entity
    *InterfaceRepository
    *InterfaceService
Core:
    *Repository
    *Services
Services:
    *Controllers
Vo:
    *Manejo de datos para peticiones y respuestas

Para la documentación de las APIs, se utilizó Swagger, y se puede acceder de manera local a través de la URL: http://localhost:8080/swagger-ui.html.

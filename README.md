# Foro Challenge Alura

Este proyecto es un challenge de parte de Alura, que consiste en la creación de un foro utilizando Java y Spring Boot. 
El foro permite la gestión de cursos, respuestas, tópicos y usuarios, incluyendo operaciones CRUD (crear, leer, actualizar y eliminar) para cada uno de estos recursos.

## Características

- **Gestión de Cursos:** Permite crear, editar, consultar y eliminar cursos.
- **Gestión de Respuestas:** Los usuarios pueden crear, editar, consultar y eliminar respuestas en los tópicos.
- **Gestión de Tópicos:** Los tópicos pueden ser creados, editados, consultados y eliminados.
- **Gestión de Usuarios:** Los usuarios pueden registrarse, editar su información, consultar sus datos y eliminar su cuenta.
- **Autenticación y Autorización:** Utiliza JWT (JSON Web Tokens) para asegurar que solo los usuarios autenticados puedan acceder a ciertas funcionalidades.
- **Validaciones:** Asegura la integridad de los datos mediante validaciones en los controladores y en las entidades.
- **Migraciones de Base de Datos:** Utiliza Flyway para gestionar las migraciones y versionar la base de datos.
- **Documentación de la API:** La especificación de la API se genera automáticamente con SpringDoc y se publica en un endpoint.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
  - Spring Data JPA
  - Spring Security
  - Spring Web
  - SpringDoc
- **PostgreSQL**
- **Flyway**

## Estructura del Proyecto

El proyecto está organizado de la siguiente manera:

- **src/main/java:** Contiene el código fuente del proyecto.
  - **controller:** Contiene los controladores REST para manejar las solicitudes HTTP.
  - **model:** Contiene las entidades JPA que representan las tablas de la base de datos.
  - **repository:** Contiene las interfaces que extienden JpaRepository para interactuar con la base de datos.
  - **service:** Contiene la lógica de negocio de la aplicación.
  - **security:** Contiene las clases relacionadas con la seguridad y la autenticación.
- **src/main/resources:** Contiene los recursos del proyecto, como los archivos de configuración y las migraciones de Flyway.

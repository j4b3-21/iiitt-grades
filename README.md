# 🚀 IIITT-Grading System

This is a **Spring Boot backend application** built using **Maven** for
managing grading workflows at IIITT. The project follows a **clean,
layered architecture** suitable for real-world and scalable
applications.

------------------------------------------------------------------------

## 🧰 Tech Stack

-   **Java** (25 recommended)
-   **Spring Boot**
-   **Maven**
-   **Spring Web**
-   **Spring Data JPA**
-   **Spring Security** (JWT-ready)
-   **PostgreSQL**
-   **Hibernate**


------------------------------------------------------------------------

## 📁 Project Structure

    project-root
    │
    ├── pom.xml
    │
    └── src
        ├── main
        │   ├── java
        │   │   └── com.example.grading
        │   │       ├── GradingApplication.java
        │   │       ├── controller
        │   │       ├── service
        │   │       │   └── impl
        │   │       ├── repository
        │   │       ├── model
        │   │       │   └── entity
        │   │       ├── dto
        │   │       ├── mapper
        │   │       ├── exception
        │   │       ├── security
        │   │       ├── config
        │   │       ├── util
        │   │       └── enums
        │   │
        │   └── resources
        │       ├── application.properties
        │       ├── static
        │       ├── templates
        │       └── db
        │           └── migration
        │
        └── test
            └── java

------------------------------------------------------------------------

## 🚀 Running the Application
MacOS,Linux
``` bash
./mvnw spring-boot:run
```
Windows
``` 
mvnw.cmd spring-boot:run
```


Application runs at:

    http://localhost:8080

------------------------------------------------------------------------

---

## 📦 Package Responsibilities

### `controller`
- Handles HTTP requests (`GET`, `POST`, `PUT`, `DELETE`)
- Entry point for APIs
- No business logic

### `service`
- Contains business logic
- Validations and transactions
- Interfaces + implementations (`service/impl`)

### `repository`
- Database access layer
- Extends `JpaRepository`
- Contains database queries

### `model/entity`
- JPA entities
- Maps to database tables

### `dto`
- Data Transfer Objects
- Request and response models
- Prevents exposing entities directly

### `mapper`
- Converts DTOs ↔ Entities
- Keeps code clean and reusable

### `exception`
- Custom exceptions
- Global exception handling using `@ControllerAdvice`

### `security`
- Spring Security configuration
- JWT filters and utilities
- Role-based access control

### `config`
- Application configuration
- CORS, Swagger, custom beans

### `util`
- Helper and utility classes
- Common reusable logic

### `enums`
- All enums are present here

---

## ⚙️ Configuration (`application.properties`)

The `application.properties` file is used to configure:
- Server port
- Database connection
- JPA/Hibernate settings
- Logging levels


```properties
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/iiitt_grades
spring.datasource.username=postgres
spring.datasource.password=postres

spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.open-in-view=false
spring.jpa.show-sql=false
```
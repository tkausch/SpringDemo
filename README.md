# SpringDemo — REST API with Spring Boot

A sample project showing how to build and publish a REST API with Spring Boot. My first Spring Boot app in years — things have gotten a lot nicer!

## What this project covers

- Building a REST API with `@RestController`
- API documentation with Swagger UI (springdoc-openapi)
- Two interchangeable storage strategies via the **Strategy pattern**:
  - `in-memory` — stores data in a plain Java `ArrayList`, no database needed
  - `jpa` — stores data in an H2 in-memory database using Spring Data JPA
- Switching strategies without changing any code, just a config property

## Requirements

- Java 21+
- Maven (or use the included `./mvnw` wrapper)

## Run the app

```bash
./mvnw spring-boot:run
```

The app starts on **port 8081**.

## Switch storage strategy

In `src/main/resources/application.properties`:

```properties
# Use in-memory list (no database)
spring.profiles.active=in-memory

# Use H2 database via JPA
spring.profiles.active=jpa
```

Both strategies are pre-loaded with two sample books on startup.

## API endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/books` | Get all books |
| GET | `/api/books/{id}` | Get a book by ID |
| POST | `/api/books` | Create a new book |

### Example request

```bash
curl -X POST http://localhost:8081/api/books \
  -H "Content-Type: application/json" \
  -d '{"title": "Domain-Driven Design", "author": "Eric Evans"}'
```

## Swagger UI

Interactive API docs are available at:

```
http://localhost:8081/swagger-ui/index.html
```

Export the OpenAPI spec:
- JSON: `http://localhost:8081/v3/api-docs`
- YAML: `http://localhost:8081/v3/api-docs.yaml`

## H2 Console (jpa profile only)

When running with `spring.profiles.active=jpa`, the H2 web console is available at:

```
http://localhost:8081/h2-console
```

JDBC URL: `jdbc:h2:mem:bookdb`

## Build a runnable JAR

```bash
./mvnw package -DskipTests
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

## Project structure

```
src/main/java/com/example/demo/
├── Book.java                    # JPA entity / data model
├── BookRepository.java          # Strategy interface
├── InMemoryBookRepository.java  # In-memory implementation
├── JpaBookRepository.java       # JPA implementation
├── BookService.java             # Business logic
├── BookController.java          # REST endpoints
└── DataInitializer.java         # Sample data for both profiles
```

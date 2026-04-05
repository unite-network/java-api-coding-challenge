# Addresses API

A Spring Boot REST API for managing user addresses with filtering, validation, and containerized deployment.

## Changes Performed

### API Enhancements

- **GET /api/addresses** - Retrieve addresses with optional filtering by userId and addressType using dynamic JPA Specifications
- **POST /api/addresses** - Create new addresses with UUID auto-generation and comprehensive validation
- **Request/Response Layer** - Introduced AddressCreateRequestDTO to separate API contract from entity model

### Data Validation & Error Handling

- Global exception handler with custom error messages for deserialization errors, method argument mismatches, and validation failures
- Field-level validation on userId and addressType (required fields enforced at both application and database level)
- Automatic UUID conversion and validation for both request parameters and request bodies

### Entity & Architecture

- Added Lombok for reducing boilerplate code (@Getter, @Setter, @Builder patterns)
- Add Spring Data JPA for repository layer with dynamic query capabilities using Specifications
- Improved code structure with clear separation of concerns (controller, service, repository, DTOs, exceptions)

### Infrastructure

- Multi-stage Dockerfile for optimized image build and runtime
- Containerized application with proper port exposure (8080)
- JPA Specification-based dynamic query filtering (replacing multiple query methods)

## Quick Start

Build:

```bash
mvn clean package
```

Run locally:

```bash
./mvnw spring-boot:run
```

Docker:

```bash
docker build -t addresses-api:1.0.1 .
docker run -p 8080:8080 addresses-api:1.0.1
```

## Further Improvements

- Add pagination and sorting to GET endpoint
- Implement update (PUT/PATCH) and delete (DELETE) endpoints
- Add integration tests and API contract tests
- Implement authentication and authorization
- Add OpenAPI/Swagger documentation
- Configure database persistence (replace H2 with PostgreSQL)
- Add request logging and distributed tracing
- Implement rate limiting and caching strategies

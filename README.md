# Spring Core Training Management System

## Overview
This project is a Spring Core-based application designed to manage training sessions, trainees, and trainers. It follows best practices for dependency injection, in-memory storage, and externalized configuration.

## Features
- **Spring Application Context**: Configured using annotations and Java-based configuration.
- **DAO Implementation**: Each entity (Trainer, Trainee, Training) has a dedicated DAO storing data in a separate Java `Map` namespace.
- **Storage as a Spring Bean**: Implemented as a separate bean, initialized with data from external files using bean post-processing.
- **Property-Based Configuration**: Storage file paths are set using external properties.
- **Dependency Injection**:
    - Storage beans are injected into DAO beans via autowiring.
    - Service beans are injected into the facade using constructor-based injection.
    - Other dependencies use setter-based injection.
- **Unit Testing**: Code is covered with unit tests to ensure reliability.
- **Logging**: Proper logging is implemented for debugging and monitoring.
- **Profile Functionality for Trainees & Trainers**:
    - Username is generated as `firstName.lastName`.
    - If a duplicate exists, a serial number is appended (e.g., `John.Smith.1`).
    - Passwords are randomly generated with a 10-character length.

## Technologies Used
- Java 17+
- Spring Core
- Spring Beans
- Spring Configuration Properties
- JUnit 5
- Mockito
- SLF4J for Logging

## Getting Started
### Prerequisites
- Java 17+
- Maven or Gradle
- An IDE (IntelliJ IDEA, Eclipse, VS Code)

### Configuration
Ensure the following property is set in `application.properties`:
```
data.trainees.file=src/main/resources/trainees.csv
```

### Running the Application
1. Clone the repository:
   ```sh
   git clone <repo-url>
   cd training-management-system
   ```
2. Build the project:
   ```sh
   mvn clean install
   ```
3. Run the application:
   ```sh
   mvn spring-boot:run
   ```

### Running Tests
```sh
mvn test
```

## Notes
This project fully aligns with the following specifications:
1. **Spring Application Context**: Configured using annotations or Java-based approach.
2. **DAO Implementation**: Data is stored in an in-memory `Map`, with each entity type having its own namespace.
3. **Storage Initialization**: Implemented as a Spring bean, loading data from an external file using bean post-processing.
4. **Dependency Injection**:
    - Storage injected into DAOs via autowiring.
    - Service beans injected into the facade using constructor-based injection.
    - Other dependencies injected via setters.
5. **Unit Testing**: Covered with appropriate unit tests.
6. **Logging**: Proper logging is implemented.
7. **User Profile Management**:
    - Usernames are `firstName.lastName`, with a serial number for duplicates.
    - Passwords are randomly generated (10 characters long).

## Author
- **Mironshoh**


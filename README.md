# Mercury - SMTP - Simple Email Server

An SMTP server and client implementation in Java, designed as a portfolio project demonstrating software engineering expertise.

## Project Overview
- **Purpose**: SMTP server and client implementation based on RFC 5321
- **Goal**: Demonstrate software engineering expertise and network programming capabilities
- **Scope**: Basic SMTP protocol implementation with possibility for expansion

## Dependencies

The project uses the following dependencies:

### Core Dependencies
- **Java 17+**: Modern language features and improved performance
- **Maven**: Build automation and dependency management

### Logging
- **SLF4J 2.0.9**: Logging facade over implementation details
- **Logback 1.4.11**: Performant logging

### Testing
- **JUnit 5**: Testing framework for unit and integration tests

## Technical Features

- Multi-threaded SMTP server with connection pooling
- Protocol state machine implementation
- Comprehensive error handling
- Extensible command pattern for SMTP commands
- Clean, maintainable code following SOLID principles

## Build and Run

```bash
# Clone the repository
git clone https://github.com/yourusername/mercury-smtp.git

# Navigate to the project directory
cd mercury-smtp

# Build the project
mvn clean package

# Run the server
java -jar target/mercury-smtp-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Project Structure

```
mercury-smtp/
│
├── pom.xml
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── couture/
│   │               └── mercury/
│   │                   ├── Main.java
│   │                   ├── core/
│   │                   ├── server/
│   │                   ├── client/
│   │                   ├── model/
│   │                   ├── exceptions/
│   │                   └── util/
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── couture/
│                   └── mercury/
│                       ├── core/
│                       ├── server/
│                       ├── client/
│                       └── util/
│
└── README.md
```

## Design Principles

- Modular architecture with clear separation of concerns
- Thread-safety for concurrent operations
- Defensive programming with comprehensive error handling
- Clean, readable, and self-documenting code
- Strong typing and null safety
- Performance-conscious implementation

## Development Status

This project is currently in active development.

## License

[MIT License](LICENSE)

# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is the IES (Information Enhancement System) Shared Kernel - a Java library containing generic domain models, technical base objects, and cross-cutting concerns for the IES system. It follows Domain-Driven Design principles as a shared kernel component.

## Development Commands

### Build and Compilation
```bash
mvn compile                    # Compile source code
mvn clean compile              # Clean and compile
mvn package                    # Build JAR package
mvn clean package              # Clean and build
```

### Testing
```bash
mvn test                       # Run all tests
mvn test -Dtest=ClassName      # Run specific test class
mvn test -Dtest=ClassName#methodName  # Run specific test method
mvn verify                     # Run tests and integration checks
```

### Code Quality and Analysis
```bash
mvn spotless:check             # Check code formatting (Google Java Format)
mvn spotless:apply             # Apply code formatting
mvn com.github.spotbugs:spotbugs-maven-plugin:check  # Run SpotBugs static analysis
mvn pmd:check                  # Run PMD static analysis
mvn jacoco:check               # Check code coverage requirements
```

### Documentation
```bash
mvn javadoc:javadoc           # Generate Javadoc documentation
mvn site                      # Generate project site with reports
```

## Architecture and Structure

### Core Domain Packages
- **anchor**: Contains `Anchor` class for alternative identifiers with specific validation rules (alphanumeric, underscore, minus, dot only)
- **base**: Contains fundamental types like `Identifier` which can represent either numeric IDs or `Anchor` references
- **security**: Authentication and authorization components including `User`, `Authentication`, `AuthMethod`, `AuthFactor`, and permission annotations
- **audit**: Audit logging functionality for tracking system changes

### Key Design Patterns
- **Value Objects**: `Anchor` and `Identifier` are immutable value objects with validation
- **Builder Pattern**: Used extensively (e.g., `User.Builder`) for complex object construction
- **Factory Methods**: Static factory methods like `Anchor.ofString()`, `Identifier.ofId()`
- **Domain-Driven Design**: Clear bounded contexts and shared kernel principles

### Important Constraints
- **Anchor Validation**: Must contain only letters, numbers, underscore, minus, dot - no spaces or special characters, cannot be only numbers
- **Identifier**: Can be either a numeric string ID (max 19 chars, > 0) or an `Anchor`
- **Java Version**: Requires Java 21
- **Serialization**: Key domain objects implement `Serializable` with explicit `serialVersionUID`

### Dependencies and Libraries
- **Jackson**: For JSON serialization/deserialization
- **Log4j2**: For logging
- **JUnit 5**: For testing
- **Mockito**: For mocking in tests
- **EqualsVerifier**: For testing equals/hashCode contracts
- **Hamcrest**: For test assertions

### Testing Approach
- Unit tests mirror the main package structure in `src/test/java`
- Uses JUnit 5 with Mockito for mocking
- EqualsVerifier and ToStringVerifier for contract testing
- Tests focus on validation rules, edge cases, and domain invariants

### Code Style
- Google Java Format style enforced via Spotless
- PMD rules defined in `pmd-ruleset.xml`
- SpotBugs static analysis with exclusions in `spotbug-exclude-filter.xml`
- No code comments should be added unless specifically requested
- Prefer immutable objects and defensive copying
- Use builder patterns for complex object construction

## Model/Entity Template

When creating new domain models, follow this exact pattern based on `User.java`:

### Class Structure
- `public final class` (immutable)
- `@JsonDeserialize(builder = ClassName.Builder.class)`
- `@SuppressWarnings` for PMD rules as needed
- All fields `private final`
- Use `@NotNull` for required fields
- Constructor is `private` - only accessible via Builder

### Field Access
- Accessor methods without "get" prefix (e.g., `id()` not `getId()`)
- All accessors annotated with `@JsonProperty`
- For collections: return `List.copyOf(field)` (defensive copying)
- Additional business methods can be `@JsonIgnore`

### Validation
- Constructor validates required fields with `Objects.requireNonNull()`
- Additional validation (e.g., `isBlank()`) as needed
- Clear error messages: `"fieldName cannot be null"`

### Standard Methods
- Override `hashCode()`, `equals()`, `toString()`
- Use `Objects.hash()` and `Objects.equals()`
- Use modern instanceof pattern: `if (!(obj instanceof ClassName that))`

### Builder Pattern
- Static `builder()` method returns new Builder
- `toBuilder()` method for copying existing instances
- `@JsonPOJOBuilder(withPrefix = "")`
- Private default constructor and copy constructor
- Builder methods return `this` for chaining
- All builder setters validate with `Objects.requireNonNull()`
- For collections: provide both single item and varargs methods
- Final `build()` method creates the instance

### Collection Handling
- Initialize collections in Builder as `new ArrayList<>()`
- In copy constructor: `addAll()` from source
- In main constructor: `List.copyOf()` for immutability
- Provide both `items(Item... items)` and `item(Item item)` methods

This pattern ensures consistency, immutability, proper JSON serialization, and validation across all domain models.
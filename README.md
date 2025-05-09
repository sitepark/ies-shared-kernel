# Shared Kernel

The shared kernel contains generic domain models, technical base objects, and cross-cutting concerns such as labeling
and data identification (e.g., Anchor, Identifier).

## Goal and Benefits

- **Standardization**: Central definition of shared models and policies.
- **Consistency**: Clear standards and centralized policies for cross-cutting concerns.
- **Reusability**: Avoidance of redundant implementations in individual modules.
- **Abstraction**: Provision of abstract interfaces for mechanisms that are independent of specific business logic.

## Design Principles

- **Cross-Cutting Concern**: Logic that can be used across modules.
- **Abstract Definitions**: Primarily interfaces and abstract classes, with minimal concrete implementations where
  necessary.
- **Domain Objects**: Encapsulation of domain-specific logic and rules to ensure consistency and maintainability. For
  example, the `Anchor` class serves as a domain object that provides a unique identifier with specific validation rules
  and use cases, such as foreign key mapping or ID mappings for data imports.

## Usage

To use the shared kernel, include it as a dependency in your module's `pom.xml`:

```xml
<dependency>
    <groupId>com.sitepark.ies</groupId>
    <artifactId>sharedkernel</artifactId>
    <version>1.0.0</version>
</dependency>
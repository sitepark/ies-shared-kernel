# Shared Kernel

Shared kernel containing generic domain models, technical base objects, and cross-cutting concerns such as security, labeling, and data identification (e.g., Anchor, Identifier).

## Goal and benefits

- Standardization\*\*: Central definition of authentication and authorization models.
- **Consistency**: Clear security standards and centralized security policies.
- Reusability\*\*: Avoidance of redundant security implementations in individual modules.
- Abstraction\*\*: Provision of abstract interfaces for security mechanisms that are independent of specific business logic.

## Design principles

- **Cross-Cutting Concern**: Safety logic that can be used across modules.
- **Abstract definitions**: Exclusively interfaces and abstract classes, no implementations.

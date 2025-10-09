# Unique-Property-Based Polymorphic Deserialization

## Overview

Unique-property-based polymorphic deserialization provides a clean alternative to traditional type discriminator fields (like `@type` or `@class`) for JSON deserialization of polymorphic types.

Instead of adding explicit type markers to JSON, the deserializer identifies the concrete type by detecting which unique property is present in the JSON object.

## Concept

### Traditional Approach (Type Discriminators)

Traditional polymorphic deserialization requires explicit type discriminators:

```json
{
  "@type": "UserId",
  "userId": "123"
}
```

**Problems:**
- Artificial fields that don't represent domain data
- Coupling between JSON format and deserialization mechanism
- Less intuitive JSON structure

### Unique-Property Approach

With unique-property-based deserialization, the JSON is cleaner and more intuitive:

```json
{
  "userId": "123"
}
```

The deserializer recognizes that the presence of the `userId` property identifies this as a `UserId` instance.

**Advantages:**
- ✅ Clean JSON without artificial type markers
- ✅ Self-documenting - the unique property naturally identifies the type
- ✅ Domain-focused - properties represent actual domain data
- ✅ Infrastructure-independent - no coupling to specific JSON libraries

## How It Works

### 1. Unique Property Identification

Each concrete type in a polymorphic hierarchy declares a unique property that identifies it:

```java
@UniquePropertyType(uniqueProperty = "userId")
public final class UserId implements Filter {
  private final String userId;
}

@UniquePropertyType(uniqueProperty = "entityId")
public final class EntityId implements Filter {
  private final String entityId;
}
```

### 2. Type Registration

The base interface or abstract class lists all concrete implementations:

```java
@UseUniquePropertyDeserializer(
    types = {
      UserId.class,
      EntityId.class,
      And.class,
      Or.class
    })
public interface Filter {}
```

### 3. Automatic Deserialization

During deserialization:
1. Infrastructure reads the JSON object
2. Examines which properties are present
3. Matches against registered unique properties
4. Instantiates the corresponding concrete type

```java
// JSON: {"userId": "123"}
Filter filter = objectMapper.readValue(json, Filter.class);
// Result: UserId instance
```

## Annotations

### `@UseUniquePropertyDeserializer`

**Purpose:** Marks an interface or abstract class for unique-property-based deserialization

**Target:** `TYPE` (interfaces, abstract classes)

**Parameters:**
- `types()`: Array of all concrete implementation classes

**Example:**
```java
@UseUniquePropertyDeserializer(
    types = {
      UserId.class,
      EntityId.class,
      And.class
    })
public interface Filter {}
```

### `@UniquePropertyType`

**Purpose:** Declares the unique identifying property for a concrete type

**Target:** `TYPE` (concrete classes)

**Parameters:**
- `uniqueProperty()`: The unique JSON property name that identifies this type

**Example:**
```java
@UniquePropertyType(uniqueProperty = "userId")
public final class UserId implements Filter {
  private final String userId;

  public UserId(String userId) {
    this.userId = userId;
  }

  public String getUserId() {
    return userId;
  }
}
```

## Usage Guide

### Step 1: Define the Polymorphic Hierarchy

Start with your domain interface or abstract class:

```java
package com.example.domain;

import com.sitepark.ies.sharedkernel.json.UseUniquePropertyDeserializer;

@UseUniquePropertyDeserializer(
    types = {
      UserId.class,
      EntityId.class,
      And.class,
      Or.class,
      Not.class
    })
public interface Filter {}
```

### Step 2: Implement Concrete Types

Each concrete type must declare its unique property:

```java
package com.example.domain;

import com.sitepark.ies.sharedkernel.json.UniquePropertyType;

@UniquePropertyType(uniqueProperty = "userId")
public final class UserId implements Filter {
  private final String userId;

  public UserId(String userId) {
    this.userId = userId;
  }

  public String getUserId() {
    return userId;
  }
}

@UniquePropertyType(uniqueProperty = "entityId")
public final class EntityId implements Filter {
  private final String entityId;

  public EntityId(String entityId) {
    this.entityId = entityId;
  }

  public String getEntityId() {
    return entityId;
  }
}

@UniquePropertyType(uniqueProperty = "and")
public final class And implements Filter {
  private final List<Filter> and;

  public And(List<Filter> and) {
    this.and = and;
  }

  public List<Filter> getAnd() {
    return and;
  }
}

@UniquePropertyType(uniqueProperty = "or")
public final class Or implements Filter {
  private final List<Filter> or;

  public Or(List<Filter> or) {
    this.or = or;
  }

  public List<Filter> getOr() {
    return or;
  }
}

@UniquePropertyType(uniqueProperty = "not")
public final class Not implements Filter {
  private final Filter not;

  public Not(Filter not) {
    this.not = not;
  }

  public Filter getNot() {
    return not;
  }
}
```

### Step 3: Infrastructure Setup

The infrastructure layer (e.g., Jackson module) reads these annotations at runtime and configures the appropriate deserializer. See your infrastructure documentation for specific setup instructions.

## JSON Examples

### Simple Types

```json
{"userId": "user-123"}
```
→ Creates `UserId` instance

```json
{"entityId": "entity-456"}
```
→ Creates `EntityId` instance

### Nested Types

```json
{
  "and": [
    {"userId": "user-123"},
    {"entityId": "entity-456"}
  ]
}
```
→ Creates `And` instance containing `UserId` and `EntityId`

### Complex Nested Structures

```json
{
  "or": [
    {"userId": "user-123"},
    {
      "and": [
        {"entityId": "entity-456"},
        {"userId": "user-789"}
      ]
    },
    {
      "not": {
        "userId": "user-999"
      }
    }
  ]
}
```
→ Creates nested structure with `Or`, `And`, `Not`, and multiple `UserId` and `EntityId` instances

## Important Rules

### Rule 1: Unique Properties Must Be Actual Fields

The unique property must exist as a field in the annotated class:

**✅ Correct:**
```java
@UniquePropertyType(uniqueProperty = "userId")
public final class UserId {
  private final String userId;  // Field exists
}
```

**❌ Wrong:**
```java
@UniquePropertyType(uniqueProperty = "userName")
public final class UserId {
  private final String userId;  // Field name doesn't match!
}
```

### Rule 2: Unique Properties Must Be Unique

Each type in the polymorphic hierarchy must have a different unique property:

**✅ Correct:**
```java
@UniquePropertyType(uniqueProperty = "userId")
public final class UserId implements Filter {}

@UniquePropertyType(uniqueProperty = "entityId")
public final class EntityId implements Filter {}
```

**❌ Wrong - Duplicate Property:**
```java
@UniquePropertyType(uniqueProperty = "userId")
public final class UserId implements Filter {}

@UniquePropertyType(uniqueProperty = "userId")  // Duplicate!
public final class UserFilter implements Filter {}
```

### Rule 3: All Types Must Be Registered

All concrete types must be listed in the `@UseUniquePropertyDeserializer` annotation:

**✅ Correct:**
```java
@UseUniquePropertyDeserializer(
    types = {
      UserId.class,
      EntityId.class,
      And.class  // All types listed
    })
public interface Filter {}
```

**❌ Wrong - Missing Type:**
```java
@UseUniquePropertyDeserializer(
    types = {
      UserId.class,
      EntityId.class
      // And.class is missing - won't be deserializable!
    })
public interface Filter {}
```

### Rule 4: Property Names Are Case-Sensitive

JSON property names must match exactly (case-sensitive):

```java
@UniquePropertyType(uniqueProperty = "userId")  // Expects "userId"
```

```json
{"userId": "123"}   ✅ Matches
{"UserId": "123"}   ❌ Won't match (different case)
{"userid": "123"}   ❌ Won't match (different case)
```

## Architecture Benefits

This pattern maintains clean architecture principles:

### Layer Separation

1. **Domain Layer (Core)**
   - Contains only lightweight annotations
   - No dependencies on JSON libraries
   - Focused on business logic

2. **Shared Kernel**
   - Provides domain-agnostic annotation definitions
   - No implementation code
   - Reusable across projects

3. **Infrastructure Layer**
   - Implements actual deserialization logic
   - Reads annotations at runtime
   - Library-specific (e.g., Jackson, Gson)

4. **Application Layer**
   - Registers infrastructure components
   - Wires everything together

### Dependency Flow

```
Application
    ↓
Infrastructure (reads annotations, implements deserialization)
    ↓
Shared Kernel (annotation definitions)
    ↑
Domain Core (annotated types)
```

**Key Principle:** Domain never depends on infrastructure - only on annotations from shared-kernel!

## Benefits Summary

### For Developers

- **Cleaner Code**: No boilerplate type discriminator logic
- **Type Safety**: Compile-time checking of registered types
- **Self-Documenting**: Annotations clearly show deserialization behavior
- **Easier Maintenance**: Changes to type hierarchy only require updating annotations

### For API Consumers

- **Intuitive JSON**: Natural property names without artificial markers
- **Smaller Payloads**: No redundant type discriminator fields
- **Better Readability**: JSON structure matches domain model directly
- **Easier Integration**: Standard JSON without custom conventions

### For Architecture

- **Clean Architecture**: Domain independent of infrastructure
- **Reusability**: Annotations work with any infrastructure implementation
- **Flexibility**: Easy to add new types or change infrastructure
- **Testability**: Domain logic testable without infrastructure

## Common Use Cases

### 1. Filter/Query DSL

```java
@UseUniquePropertyDeserializer(types = {
  UserId.class, EntityId.class, And.class, Or.class, Not.class
})
public interface Filter {}
```

Perfect for query languages where filters can be combined in various ways.

### 2. Event Sourcing

```java
@UseUniquePropertyDeserializer(types = {
  UserCreated.class, UserUpdated.class, UserDeleted.class
})
public interface DomainEvent {}
```

Each event type has its own unique data structure.

### 3. Command Pattern

```java
@UseUniquePropertyDeserializer(types = {
  CreateUser.class, UpdateUser.class, DeleteUser.class
})
public interface Command {}
```

Different commands with different parameters.

### 4. Notification Types

```java
@UseUniquePropertyDeserializer(types = {
  EmailNotification.class, SmsNotification.class, PushNotification.class
})
public interface Notification {}
```

Each notification channel has its own configuration.

## Troubleshooting

### Error: "No registered unique properties found"

**Problem:** The JSON object doesn't contain any registered unique property.

**Solution:** Check that:
1. The JSON property name matches the `uniqueProperty` value exactly (case-sensitive)
2. The type is registered in `@UseUniquePropertyDeserializer`
3. The type has a `@UniquePropertyType` annotation

### Error: "Type lacks @UniquePropertyType annotation"

**Problem:** A type is listed in `@UseUniquePropertyDeserializer` but doesn't have `@UniquePropertyType`.

**Solution:** Add the `@UniquePropertyType` annotation to the concrete type.

### Error: "Duplicate unique property found"

**Problem:** Two or more types declare the same unique property.

**Solution:** Each type must have a unique property name. Change one of the property names.

### Deserialization Returns Wrong Type

**Problem:** JSON is deserialized to an unexpected type.

**Solution:** Check that:
1. The unique property name is truly unique across all types
2. The JSON contains the correct property name
3. Multiple types don't share similar property names

## Related Documentation

- [RawJson Annotation](rawjson.md) - For embedding raw JSON strings in objects
- Infrastructure documentation - For setup instructions (e.g., Jackson module)

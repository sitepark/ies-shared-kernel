# RawJson Annotation

## Overview

The `@RawJson` annotation enables bidirectional conversion between JSON strings and parsed JSON objects during serialization and deserialization. This is useful when you need to store JSON content as a string in your domain model but want it to be properly parsed in the JSON representation.

## Purpose

Sometimes domain objects need to contain JSON data as strings (e.g., for storage in databases or for flexibility), but when serializing to JSON, you want that string content to be represented as actual JSON structure rather than an escaped string.

## How It Works

### Without @RawJson

Without the annotation, JSON strings are serialized as escaped strings:

```java
public class Document {
  private String metadata;  // Contains: {"author": "John", "version": 1}
}
```

Serialized JSON:
```json
{
  "metadata": "{\"author\": \"John\", \"version\": 1}"
}
```

### With @RawJson

With the annotation, JSON strings are serialized as parsed JSON:

```java
import com.sitepark.ies.sharedkernel.json.RawJson;

public class Document {
  @RawJson
  private String metadata;  // Contains: {"author": "John", "version": 1}
}
```

Serialized JSON:
```json
{
  "metadata": {
    "author": "John",
    "version": 1
  }
}
```

## Annotation Details

**Target:** `FIELD`, `METHOD`
**Retention:** `RUNTIME`

```java
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RawJson {}
```

## Usage Examples

### Basic Usage on Field

```java
import com.sitepark.ies.sharedkernel.json.RawJson;

public class Configuration {
  private String name;

  @RawJson
  private String settings;  // Stored as JSON string

  public Configuration(String name, String settings) {
    this.name = name;
    this.settings = settings;
  }

  // Getters and setters
}
```

**Java Object:**
```java
Configuration config = new Configuration(
  "app-config",
  "{\"theme\": \"dark\", \"language\": \"en\"}"
);
```

**Serialized JSON:**
```json
{
  "name": "app-config",
  "settings": {
    "theme": "dark",
    "language": "en"
  }
}
```

**Deserialized back to Java:**
```java
// settings field contains: "{\"theme\": \"dark\", \"language\": \"en\"}"
String theme = // Parse settings string to get theme
```

### Usage on Getter Method

```java
import com.sitepark.ies.sharedkernel.json.RawJson;

public class Report {
  private String data;

  public Report(String data) {
    this.data = data;
  }

  @RawJson
  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}
```

### Complex Nested JSON

```java
import com.sitepark.ies.sharedkernel.json.RawJson;

public class AuditLog {
  private String id;
  private String action;

  @RawJson
  private String forwardData;  // JSON representing new state

  @RawJson
  private String backwardData; // JSON representing previous state

  public AuditLog(String id, String action, String forwardData, String backwardData) {
    this.id = id;
    this.action = action;
    this.forwardData = forwardData;
    this.backwardData = backwardData;
  }

  // Getters and setters
}
```

**Java Object:**
```java
AuditLog log = new AuditLog(
  "log-123",
  "UPDATE_USER",
  "{\"name\": \"Jane\", \"email\": \"jane@example.com\"}",
  "{\"name\": \"John\", \"email\": \"john@example.com\"}"
);
```

**Serialized JSON:**
```json
{
  "id": "log-123",
  "action": "UPDATE_USER",
  "forwardData": {
    "name": "Jane",
    "email": "jane@example.com"
  },
  "backwardData": {
    "name": "John",
    "email": "john@example.com"
  }
}
```

## Bidirectional Conversion

The annotation works in both directions:

### Serialization (Object → JSON)

1. Infrastructure reads the annotated field/method
2. Parses the JSON string value
3. Embeds the parsed structure in the output JSON

### Deserialization (JSON → Object)

1. Infrastructure encounters nested JSON object
2. Converts it to a JSON string
3. Sets the string value on the annotated field

## Use Cases

### 1. Flexible Schema Storage

Store dynamic or schema-less data as JSON strings in your domain model:

```java
public class Product {
  private String id;
  private String name;

  @RawJson
  private String attributes;  // Flexible product attributes
}
```

Different products can have different attributes without changing the domain model.

### 2. Database Storage

Store JSON as TEXT in databases while having proper JSON representation in APIs:

```java
public class UserPreferences {
  @RawJson
  private String settings;  // Stored as TEXT in database
}
```

### 3. Event Sourcing

Store event payloads as JSON strings while having structured JSON in event streams:

```java
public class DomainEvent {
  private String eventType;
  private Instant timestamp;

  @RawJson
  private String payload;  // Event-specific data
}
```

### 4. Audit Logging

Store detailed change information as JSON:

```java
public class ChangeLog {
  private String entity;

  @RawJson
  private String oldValue;

  @RawJson
  private String newValue;
}
```

## Infrastructure Requirements

The `@RawJson` annotation is infrastructure-agnostic. Infrastructure implementations (e.g., Jackson module) need to:

1. Detect the annotation on fields/methods
2. Parse JSON strings during serialization
3. Stringify JSON objects during deserialization

See your infrastructure documentation for setup instructions.

## Important Considerations

### Valid JSON Required

The string value must be valid JSON:

**✅ Valid:**
```java
"{\"key\": \"value\"}"
"[1, 2, 3]"
"\"simple string\""
"123"
"true"
"null"
```

**❌ Invalid:**
```java
"not json"
"{incomplete"
"'single quotes'"  // JSON requires double quotes
```

### Null Handling

Null strings are handled gracefully:

```java
@RawJson
private String data = null;
```

Serializes to:
```json
{
  "data": null
}
```

### Empty Strings

Empty strings should be avoided or handled as null:

```java
@RawJson
private String data = "";  // Potentially problematic
```

### String Escaping

The string must be properly escaped JSON:

```java
// Correct
String json = "{\"name\": \"John\"}";

// Wrong - not escaped
String json = "{"name": "John"}";  // Won't compile anyway
```

## Performance Considerations

### Parsing Overhead

Each serialization/deserialization involves parsing JSON strings:
- **Serialization**: Parse string → JSON structure
- **Deserialization**: Stringify JSON structure → string

For frequently accessed data, consider:
1. Caching parsed values
2. Using typed objects instead of strings internally
3. Lazy parsing strategies

### Memory Usage

Storing large JSON as strings requires memory for:
1. The string itself
2. The parsed JSON structure (during serialization)

For very large JSON documents, consider streaming approaches.

## Best Practices

### 1. Validate JSON

Validate JSON strings before storing:

```java
public void setMetadata(String metadata) {
  // Validate JSON format
  try {
    objectMapper.readTree(metadata);
    this.metadata = metadata;
  } catch (JsonProcessingException e) {
    throw new IllegalArgumentException("Invalid JSON", e);
  }
}
```

### 2. Document Expected Structure

Document what JSON structure is expected:

```java
/**
 * User preferences in JSON format.
 *
 * Expected structure:
 * {
 *   "theme": "light|dark",
 *   "language": "en|de|fr",
 *   "notifications": {
 *     "email": true|false,
 *     "push": true|false
 *   }
 * }
 */
@RawJson
private String preferences;
```

### 3. Use Null for Missing Data

Use `null` rather than empty strings:

```java
// Good
@RawJson
private String metadata = null;

// Avoid
@RawJson
private String metadata = "";
```

### 4. Consider Typed Alternatives

For well-defined structures, consider using typed objects:

```java
// Instead of:
@RawJson
private String settings;

// Consider:
private Settings settings;  // Typed object
```

Only use `@RawJson` when you truly need string storage with JSON representation.

## Combining with Other Annotations

`@RawJson` can be combined with other annotations:

```java
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sitepark.ies.sharedkernel.json.RawJson;

public class Document {
  @JsonProperty("custom_name")
  @RawJson
  private String metadata;
}
```

## Error Handling

### Invalid JSON During Serialization

If the string contains invalid JSON during serialization:
- Infrastructure should throw an exception
- The error should clearly indicate which field has invalid JSON

### Malformed JSON During Deserialization

If the incoming JSON structure cannot be converted to a string:
- Infrastructure should handle complex structures gracefully
- Nested objects should be properly stringified

## Related Documentation

- [Unique-Property-Based Deserialization](unique-property-deserialization.md) - For polymorphic type handling
- Infrastructure documentation - For setup and configuration

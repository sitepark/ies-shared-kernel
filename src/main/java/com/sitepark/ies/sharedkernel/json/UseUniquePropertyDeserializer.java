package com.sitepark.ies.sharedkernel.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks an interface or abstract class that should use unique-property-based polymorphic
 * deserialization.
 *
 * <p>This annotation enables automatic polymorphic JSON deserialization without requiring explicit
 * type discriminator fields. Instead, the deserializer identifies the concrete type by detecting
 * which unique property is present in the JSON object.
 *
 * <p>Each concrete implementation class must be annotated with {@link UniquePropertyType} to declare
 * its unique identifying property. During deserialization, the infrastructure examines the JSON
 * properties and matches them against the registered unique properties to determine the correct
 * type to instantiate.
 *
 * <p><strong>Example:</strong>
 *
 * <pre>
 * &#64;UseUniquePropertyDeserializer(types = {UserId.class, EntityId.class, And.class})
 * public interface Filter {}
 *
 * &#64;UniquePropertyType(uniqueProperty = "userId")
 * public final class UserId implements Filter {
 *   private final String userId;
 *   // ...
 * }
 *
 * &#64;UniquePropertyType(uniqueProperty = "entityId")
 * public final class EntityId implements Filter {
 *   private final String entityId;
 *   // ...
 * }
 *
 * // JSON deserialization:
 * // {"userId": "123"} → UserId instance
 * // {"entityId": "456"} → EntityId instance
 * </pre>
 *
 * <p>The infrastructure layer automatically detects this annotation at runtime and registers the
 * appropriate deserializer with Jackson.
 *
 * @see UniquePropertyType
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseUniquePropertyDeserializer {

  /**
   * The concrete implementation classes that can be deserialized.
   *
   * <p>Each class must be annotated with {@link UniquePropertyType} to define its unique identifying
   * property. The unique property is used to determine which concrete type to instantiate during
   * JSON deserialization.
   *
   * @return array of implementation classes
   */
  Class<?>[] types();
}

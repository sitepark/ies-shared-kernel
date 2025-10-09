package com.sitepark.ies.sharedkernel.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Declares the unique identifying property for a concrete type in a polymorphic type hierarchy.
 *
 * <p>This annotation marks a concrete implementation class and specifies which JSON property
 * uniquely identifies this type during deserialization. It is used in conjunction with {@link
 * UseUniquePropertyDeserializer} to enable polymorphic JSON deserialization without requiring
 * explicit type discriminator fields (like {@code @type} or {@code @class}).
 *
 * <p>The deserializer examines the JSON object's properties and matches them against the unique
 * properties declared by all registered types to determine which concrete class to instantiate.
 *
 * <p><strong>Example:</strong>
 *
 * <pre>
 * &#64;UseUniquePropertyDeserializer(types = {UserId.class, EntityId.class})
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
 * <p><strong>Important:</strong> The unique property must be an actual field in the class and must
 * be unique across all types in the polymorphic hierarchy. Two different types cannot declare the
 * same unique property name.
 *
 * @see UseUniquePropertyDeserializer
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquePropertyType {

  /**
   * The unique JSON property name that identifies this concrete type.
   *
   * <p>This property name is used during deserialization to detect which concrete type to
   * instantiate. When the deserializer finds this property in the JSON object, it knows to create
   * an instance of this annotated class.
   *
   * <p>The property must:
   *
   * <ul>
   *   <li>Be an actual field in the annotated class
   *   <li>Be unique within the polymorphic type hierarchy
   *   <li>Match the exact JSON property name (case-sensitive)
   * </ul>
   *
   * @return the unique property name that identifies this type
   */
  String uniqueProperty();
}

package com.sitepark.ies.sharedkernel.base;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Represents an optional value that may or may not be updated in a PATCH/UPDATE operation.
 *
 * <p>This class provides a clear semantic distinction between:
 *
 * <ul>
 *   <li><strong>unchanged</strong> - The field should NOT be updated (not provided)
 *   <li><strong>should update</strong> - The field SHOULD be updated with a new value (including
 *       null or empty collections)
 * </ul>
 *
 * <p>This is particularly useful for partial update operations (PATCH requests) where you need to
 * differentiate between "field was not provided" and "field should be set to null/empty". Without
 * this distinction, you cannot represent the intent to explicitly clear a field.
 *
 * <h2>The Three States</h2>
 *
 * <table border="1">
 *   <caption>The Three States</caption>
 *   <tr>
 *     <th>Creation</th>
 *     <th>shouldUpdate()</th>
 *     <th>getValue()</th>
 *     <th>Meaning</th>
 *   </tr>
 *   <tr>
 *     <td>{@code Updatable.unchanged()}</td>
 *     <td>false</td>
 *     <td>null</td>
 *     <td>Field not provided, don't change it</td>
 *   </tr>
 *   <tr>
 *     <td>{@code Updatable.of(null)}</td>
 *     <td>true</td>
 *     <td>null</td>
 *     <td>Field explicitly set to null, clear it</td>
 *   </tr>
 *   <tr>
 *     <td>{@code Updatable.of(value)}</td>
 *     <td>true</td>
 *     <td>value</td>
 *     <td>Field has a new value, update it</td>
 *   </tr>
 * </table>
 *
 * <h2>Use Cases</h2>
 *
 * <ul>
 *   <li><strong>Partial Updates:</strong> Update only specific fields of an entity while leaving
 *       others unchanged
 *   <li><strong>Collection Updates:</strong> Distinguish between "don't change the collection" and
 *       "clear the collection"
 *   <li><strong>NULL Assignments:</strong> Explicitly set a field to null (unlike Optional)
 *   <li><strong>REST PATCH Requests:</strong> Handle optional request body fields correctly
 *   <li><strong>Form Updates:</strong> Determine whether a user cleared a field or simply didn't
 *       provide it
 * </ul>
 *
 * <h2>Practical Example: User Update Request</h2>
 *
 * <pre>
 * public class UpdateUserRequest {
 *     private final Updatable&lt;String&gt; name;
 *     private final Updatable&lt;List&lt;String&gt;&gt; roleIds;
 *     private final Updatable&lt;String&gt; department;
 *
 *     public UpdateUserRequest(String name, List&lt;String&gt; roleIds, String department) {
 *         // Convert null/present values to Updatable
 *         this.name = name != null ? Updatable.of(name) : Updatable.unchanged();
 *         this.roleIds = roleIds != null ? Updatable.of(List.copyOf(roleIds)) : Updatable.unchanged();
 *         this.department = department != null ? Updatable.of(department) : Updatable.unchanged();
 *     }
 * }
 *
 * public void updateUser(User user, UpdateUserRequest request) {
 *     // Only update fields that were explicitly provided
 *     request.getName().ifUpdateRequired(user::setName);
 *
 *     request.getRoleIds().ifUpdateRequired(roleIds -> {
 *         if (roleIds.isEmpty()) {
 *             user.clearRoles();      // Explicitly cleared
 *         } else {
 *             user.setRoles(roleIds); // Set new roles
 *         }
 *     });
 *
 *     request.getDepartment().ifUpdateRequired(dept -> {
 *         if (dept == null) {
 *             user.setDepartment(null);  // Explicitly cleared
 *         } else {
 *             user.setDepartment(dept);  // Set new value
 *         }
 *     });
 * }
 *
 * // Usage scenarios:
 * UpdateUserRequest r1 = new UpdateUserRequest(null, null, null);
 * // All fields unchanged - no updates performed
 *
 * UpdateUserRequest r2 = new UpdateUserRequest("John", List.of(), null);
 * // name updated to "John", roles cleared, department unchanged
 *
 * UpdateUserRequest r3 = new UpdateUserRequest("Jane", null, null);
 * // name updated to "Jane", roles unchanged, department unchanged
 * </pre>
 *
 * <h2>Comparison with Alternatives</h2>
 *
 * <table border="1">
 *   <caption>Comparison with Alternatives</caption>
 *   <tr>
 *     <th>Approach</th>
 *     <th>Not Provided</th>
 *     <th>Clear Field</th>
 *     <th>Update Value</th>
 *     <th>Best For</th>
 *   </tr>
 *   <tr>
 *     <td>{@code Optional&lt;T&gt;}</td>
 *     <td>Optional.empty()</td>
 *     <td>Optional.empty()</td>
 *     <td>Optional.of(value)</td>
 *     <td>Return values only</td>
 *   </tr>
 *   <tr>
 *     <td>{@code T}</td>
 *     <td>null</td>
 *     <td>null</td>
 *     <td>value</td>
 *     <td>Cannot distinguish scenarios</td>
 *   </tr>
 *   <tr>
 *     <td>{@code @Nullable T}</td>
 *     <td>null</td>
 *     <td>null</td>
 *     <td>value</td>
 *     <td>Only annotation, no runtime info</td>
 *   </tr>
 *   <tr>
 *     <td><strong>{@code Updatable&lt;T&gt;}</strong></td>
 *     <td><strong>Updatable.unchanged()</strong></td>
 *     <td><strong>Updatable.of(null)</strong></td>
 *     <td><strong>Updatable.of(value)</strong></td>
 *     <td><strong>PATCH/UPDATE operations</strong></td>
 *   </tr>
 * </table>
 *
 * <h2>Thread Safety</h2>
 *
 * This class is immutable and thread-safe. Once created, its state cannot be changed.
 *
 * <h2>Null Handling Philosophy</h2>
 *
 * Unlike {@code Optional<T>}, which avoids null, {@code Updatable<T>} embraces null as a valid,
 * explicit value in update operations. In PATCH/UPDATE scenarios, null is semantically important:
 * it means "explicitly clear this field", which is different from "don't change this field".
 *
 * @param <T> the type of the value that may be updated
 * @since 1.0.0
 * @see Optional
 * @see java.util.List
 */
@SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
public final class Updatable<T> {

  /** Whether this field should be updated. */
  private final boolean shouldUpdate;

  /**
   * The new value to update with, or null if unchanged. When {@code shouldUpdate} is {@code true},
   * this can be any value including null. When {@code shouldUpdate} is {@code false}, this is
   * always null.
   */
  private final T newValue;

  /**
   * Constructs an Updatable with the given update flag and value.
   *
   * @param shouldUpdate whether this field should be updated
   * @param newValue the new value (can be null if {@code shouldUpdate} is true)
   */
  private Updatable(boolean shouldUpdate, T newValue) {
    this.shouldUpdate = shouldUpdate;
    this.newValue = newValue;
  }

  /**
   * Creates an Updatable representing an unchanged field.
   *
   * <p>Use this when the field was not provided in the update request or should not be modified.
   * The returned instance will return {@code false} for {@link #shouldUpdate()}.
   *
   * <p><strong>Note:</strong> The returned value is null, but this indicates "unchanged", not "set
   * to null". To explicitly set a field to null, use {@link #of(Object) of(null)}.
   *
   * @param <T> the type of the value
   * @return an Updatable representing no update
   *     <pre>
   * // Field was not provided in the request
   * Updatable&lt;String&gt; name = Updatable.unchanged();
   * assert !name.shouldUpdate();
   * assert name.getValue() == null;
   * // Service decides: don't change the name
   * </pre>
   */
  public static <T> Updatable<T> unchanged() {
    return new Updatable<>(false, null);
  }

  /**
   * Creates an Updatable representing a field that should be updated with the given value.
   *
   * <p>The value can be any object, <strong>including null</strong> or empty collections. This is
   * the key difference from Optional - it allows explicit null values and distinguishes them from
   * "no update".
   *
   * <p><strong>Important distinction:</strong>
   *
   * <ul>
   *   <li>{@code Updatable.unchanged()} - Field was not provided, do NOT update (returns null)
   *   <li>{@code Updatable.of(null)} - Field should be explicitly set to NULL (returns null but
   *       shouldUpdate=true)
   *   <li>{@code Updatable.of(value)} - Field should be updated with the given value
   * </ul>
   *
   * <p>This allows you to clearly express the intent in partial update operations:
   *
   * <ul>
   *   <li>User did not provide a field → {@code Updatable.unchanged()}
   *   <li>User explicitly cleared a field (null) → {@code Updatable.of(null)}
   *   <li>User provided a new value → {@code Updatable.of(value)}
   * </ul>
   *
   * @param <T> the type of the value
   * @param value the new value for the field; <strong>can be null</strong> to explicitly clear the
   *     field
   * @return an Updatable representing an update with the given value
   *     <pre>
   * // When a field should be updated with a non-null value
   * Updatable&lt;String&gt; name = Updatable.of("John");
   * assert name.shouldUpdate();
   * assert name.getValue().equals("John");
   *
   * // When a field should be explicitly cleared/set to null
   * Updatable&lt;String&gt; description = Updatable.of(null);
   * assert description.shouldUpdate();  // Important: this IS an update!
   * assert description.getValue() == null;
   *
   * // When a field should NOT be changed
   * Updatable&lt;String&gt; unchanged = Updatable.unchanged();
   * assert !unchanged.shouldUpdate();
   * assert unchanged.getValue() == null;  // Also null, but different semantics!
   *
   * // Practical use case with collections
   * // User did not provide roles - don't change them
   * Updatable&lt;List&lt;String&gt;&gt; roles1 = Updatable.unchanged();
   *
   * // User explicitly cleared roles - remove all roles
   * Updatable&lt;List&lt;String&gt;&gt; roles2 = Updatable.of(List.of());
   *
   * // User explicitly cleared roles by sending null - also remove all
   * Updatable&lt;List&lt;String&gt;&gt; roles3 = Updatable.of(null);
   * </pre>
   *
   * @see #unchanged()
   * @see #shouldUpdate()
   * @see #getValue()
   * @see #ifUpdateRequired(Consumer)
   */
  public static <T> Updatable<T> of(T value) {
    return new Updatable<>(true, value);
  }

  /**
   * Indicates whether this field should be updated.
   *
   * <p>Returns {@code true} if this Updatable was created with {@link #of(Object)}, indicating that
   * an update should be performed. Returns {@code false} if created with {@link #unchanged()},
   * indicating no update should occur.
   *
   * <p>This is the primary method to determine whether to apply an update, regardless of the actual
   * value (which might be null).
   *
   * @return {@code true} if the field should be updated, {@code false} otherwise
   *     <pre>
   * if (request.getName().shouldUpdate()) {
   *     // Perform the update with getValue() (which might be null)
   *     user.setName(request.getName().getValue());
   * }
   * </pre>
   */
  public boolean shouldUpdate() {
    return shouldUpdate;
  }

  /**
   * Returns the new value to update with.
   *
   * <p>If {@link #shouldUpdate()} returns {@code false}, this method returns {@code null}. If
   * {@link #shouldUpdate()} returns {@code true}, this method returns the update value, which can
   * also be {@code null}.
   *
   * <p><strong>Critical:</strong> Always check {@link #shouldUpdate()} before using this method to
   * distinguish between:
   *
   * <ul>
   *   <li>{@code shouldUpdate() = false && getValue() = null} → "unchanged" (don't update)
   *   <li>{@code shouldUpdate() = true && getValue() = null} → "explicitly set to null" (do update)
   * </ul>
   *
   * @return the new value, or null if unchanged (or if the value was set to null)
   * @see #shouldUpdate()
   * @see #ifUpdateRequired(Consumer)
   *     <pre>
   * Updatable&lt;String&gt; name = Updatable.unchanged();
   * assert name.getValue() == null;
   * assert !name.shouldUpdate();
   * // Result: Don't update the field
   *
   * Updatable&lt;String&gt; name2 = Updatable.of(null);
   * assert name2.getValue() == null;
   * assert name2.shouldUpdate();  // Different!
   * // Result: Update the field and set it to null
   * </pre>
   */
  public T getValue() {
    return newValue;
  }

  /**
   * Performs an action if this field should be updated.
   *
   * <p>This is a convenience method for applying conditional logic based on whether the field
   * should be updated. The provided Consumer will only be invoked if {@link #shouldUpdate()}
   * returns {@code true}.
   *
   * <p>The Consumer receives the new value, which can be null. Use this pattern to handle updates
   * consistently without needing to check {@code shouldUpdate()} separately.
   *
   * @param action the action to perform with the new value; must not be null
   * @throws NullPointerException if {@code action} is null
   *     <pre>
   * // Simple field update
   * request.getName().ifUpdateRequired(user::setName);
   *
   * // Complex logic with null handling
   * request.getDepartment().ifUpdateRequired(dept -> {
   *     if (dept == null) {
   *         user.clearDepartment();
   *     } else {
   *         user.setDepartment(dept);
   *     }
   * });
   *
   * // Collection update with clear semantics
   * request.getRoles().ifUpdateRequired(roles -> {
   *     if (roles == null || roles.isEmpty()) {
   *         user.clearRoles();
   *     } else {
   *         user.setRoles(roles);
   *     }
   * });
   * </pre>
   */
  public void ifUpdateRequired(Consumer<T> action) {
    if (shouldUpdate) {
      action.accept(newValue);
    }
  }

  /**
   * Converts this Updatable to an Optional.
   *
   * <p>If {@link #shouldUpdate()} returns {@code false}, returns {@code Optional.empty()}. If
   * {@link #shouldUpdate()} returns {@code true}, returns {@code Optional.ofNullable(value)}, which
   * means that "update to null" becomes {@code Optional.empty()}.
   *
   * <p><strong>Warning:</strong> This conversion loses information! It cannot distinguish between
   * "unchanged" and "update to null". Only use this method if:
   *
   * <ul>
   *   <li>You don't need to differentiate between null and unchanged, OR
   *   <li>You're passing the result to code that expects Optional
   * </ul>
   *
   * @return an Optional representing this value
   *     <pre>
   * Updatable&lt;String&gt; name = Updatable.of(null);
   * Optional&lt;String&gt; opt = name.toOptional();
   * // opt.isEmpty() is true, but we've lost the information that an update was requested
   * // Only use this if you don't care about the difference
   * </pre>
   */
  public Optional<T> toOptional() {
    return shouldUpdate ? Optional.ofNullable(newValue) : Optional.empty();
  }

  /**
   * Returns a string representation of this Updatable.
   *
   * @return a string representation including update status and value
   */
  @Override
  public String toString() {
    return "Updatable{" + "shouldUpdate=" + shouldUpdate + ", newValue=" + newValue + '}';
  }

  /**
   * Indicates whether some other object is equal to this Updatable.
   *
   * <p>Two Updatables are equal if they have the same update status and the same value.
   *
   * @param o the object to be compared
   * @return true if the objects are equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Updatable<?> updatable)) return false;
    return shouldUpdate == updatable.shouldUpdate && Objects.equals(newValue, updatable.newValue);
  }

  /**
   * Returns a hash code for this Updatable.
   *
   * @return the hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(shouldUpdate, newValue);
  }
}

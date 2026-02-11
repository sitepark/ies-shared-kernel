package com.sitepark.ies.sharedkernel.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
public final class EntityRef implements Comparable<EntityRef> {

  private final String type;

  private final String id;

  private EntityRef(String type, String id) {
    Objects.requireNonNull(type, "type must not be null");
    Objects.requireNonNull(id, "id must not be null");
    this.type = type;
    this.id = id;
  }

  public static EntityRef of(Class<?> type, String id) {
    return new EntityRef(toTypeString(type), id);
  }

  @JsonCreator
  public static EntityRef of(@JsonProperty("type") String type, @JsonProperty("id") String id) {
    return new EntityRef(type, id);
  }

  public static String toTypeString(Class<?> type) {
    if (type == null) {
      return null;
    }
    return type.getSimpleName().toLowerCase(Locale.ROOT);
  }

  public static boolean isType(Class<?> roleClass, String s) {
    return toTypeString(roleClass).equals(s);
  }

  @JsonProperty
  public String id() {
    return id;
  }

  @JsonProperty
  public String type() {
    return type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof EntityRef that)
        && Objects.equals(this.type, that.type)
        && Objects.equals(this.id, that.id);
  }

  @Override
  public int compareTo(@NotNull EntityRef that) {
    return Objects.compare(
        this, that, Comparator.comparing(EntityRef::type).thenComparing(EntityRef::id));
  }

  @Override
  public String toString() {
    return "EntityRef{" + "type='" + type + '\'' + ", id='" + id + '\'' + '}';
  }
}

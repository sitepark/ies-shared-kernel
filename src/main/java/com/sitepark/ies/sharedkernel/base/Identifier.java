package com.sitepark.ies.sharedkernel.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sitepark.ies.sharedkernel.anchor.Anchor;
import java.util.Objects;
import java.util.function.Function;
import org.jetbrains.annotations.Nullable;

/**
 * The {@code Identifier} class represents a unique identifier that can either be an ID (a numeric
 * string) or an {@link Anchor}.
 *
 * <p>This class provides methods to create an {@code Identifier} from either an ID or an {@code
 * Anchor}, ensuring that the provided values conform to the expected format and constraints. The
 * {@code Identifier} is immutable and guarantees that either an ID or an {@code Anchor} is always
 * present, but not both simultaneously.
 *
 * <p>The {@link Anchor} represents a domain-specific reference, which can be used as an alternative
 * to numeric IDs for identifying entities.
 *
 * <p>Example usage:
 *
 * <pre>
 *     Identifier id = Identifier.ofId("12345");
 *     Identifier anchorIdentifier = Identifier.ofAnchor(Anchor.ofString("example-anchor"));
 * </pre>
 *
 * @see Anchor
 */
public final class Identifier {

  private static final int MAX_ID_LENGTH = 19;

  private static final String ZERO_ID = "0";

  private final String id;

  private final Anchor anchor;

  private Identifier(String id) {
    this.id = id;
    this.anchor = null;
  }

  private Identifier(Anchor anchor) {
    this.id = null;
    this.anchor = anchor;
  }

  public static Identifier ofId(String id) {
    Objects.requireNonNull(id, "id is null");
    if (!isId(id)) {
      throw new IllegalArgumentException("invalid id: " + id);
    }
    return new Identifier(id);
  }

  public static Identifier ofAnchor(String anchor) {
    Objects.requireNonNull(anchor, "anchor is null");
    return Identifier.ofAnchor(Anchor.ofString(anchor));
  }

  public static Identifier ofAnchor(Anchor anchor) {
    Objects.requireNonNull(anchor, "anchor is null");
    if (anchor == Anchor.EMPTY) {
      throw new IllegalArgumentException("anchor is empty");
    }
    return new Identifier(anchor);
  }

  @JsonCreator
  public static Identifier ofString(String identifier) {
    Objects.requireNonNull(identifier, "identifier is null");
    if (identifier.isBlank()) {
      throw new IllegalArgumentException("identifier is blank");
    }
    if (isId(identifier)) {
      return new Identifier(identifier);
    }
    return new Identifier(Anchor.ofString(identifier));
  }

  public static boolean isId(String str) {

    if (ZERO_ID.equals(str)) {
      throw new IllegalArgumentException("id should be greater than 0");
    }

    int length = str.length();
    if (length > MAX_ID_LENGTH) {
      return false;
    }

    for (int i = 0; i < length; i++) {
      char c = str.charAt(i);
      if ((c < '0') || (c > '9')) {
        return false;
      }
    }
    return true;
  }

  public String resolveId(Function<Anchor, String> anchorResolver) {
    if (this.id != null) {
      return this.id;
    }
    assert this.anchor != null : "Neither id nor anchor is set";
    return anchorResolver.apply(this.anchor);
  }

  @Nullable
  public String getId() {
    return this.id;
  }

  @Nullable
  public Anchor getAnchor() {
    return this.anchor;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.anchor);
  }

  @Override
  public boolean equals(Object o) {

    if (!(o instanceof Identifier that)) {
      return false;
    }

    return Objects.equals(this.id, that.id) && Objects.equals(this.anchor, that.anchor);
  }

  @JsonValue
  @Override
  public String toString() {
    if (this.id != null) {
      return this.id;
    }
    assert this.anchor != null;
    return this.anchor.toString();
  }
}

package com.sitepark.ies.sharedkernel.email;

import java.util.Objects;
import org.jspecify.annotations.NonNull;

public record EmailMessageTypeIdentifier(String category, String key) {
  public static EmailMessageTypeIdentifier of(String category, String key) {
    return new EmailMessageTypeIdentifier(category, key);
  }

  public static EmailMessageTypeIdentifier parse(String s) {
    Objects.requireNonNull(s, "s must not be null");
    String[] parts = s.split(":", 2);
    int validParsedPartsLength = 2;
    if (parts.length != validParsedPartsLength) {
      throw new IllegalArgumentException("invalid message-type identifier: " + s);
    }
    if (parts[0].isBlank()) {
      throw new IllegalArgumentException("messageType category must not be blank");
    }
    if (parts[1].isBlank()) {
      throw new IllegalArgumentException("messageType key must not be blank");
    }

    return EmailMessageTypeIdentifier.of(parts[0], parts[1]);
  }

  /**
   * Returns the string representation in format "category:key".
   *
   * @return the formatted identifier
   */
  @Override
  public @NonNull String toString() {
    return category + ":" + key;
  }
}

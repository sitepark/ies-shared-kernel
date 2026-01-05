package com.sitepark.ies.sharedkernel.email;

import java.util.Objects;
import java.util.regex.Pattern;
import javax.annotation.concurrent.Immutable;

@Immutable
public record EmailMessageThemeIdentifier(String category, String key) {

  private static final Pattern VALID_PATTERN = Pattern.compile("^[a-zA-Z0-9-_]+$");

  private static final int EXPECTED_PARTS = 2;

  public EmailMessageThemeIdentifier {

    Objects.requireNonNull(category, "category must not be null");
    Objects.requireNonNull(key, "key must not be null");

    if (category.isBlank()) {
      throw new IllegalArgumentException("theme category must not be blank");
    }
    if (key.isBlank()) {
      throw new IllegalArgumentException("theme key must not be blank");
    }

    if (!VALID_PATTERN.matcher(category).matches()) {
      throw new IllegalArgumentException("category contains invalid characters: " + category);
    }
    if (!VALID_PATTERN.matcher(key).matches()) {
      throw new IllegalArgumentException("key contains invalid characters: " + key);
    }
  }

  public static EmailMessageThemeIdentifier of(String category, String key) {
    return new EmailMessageThemeIdentifier(category, key);
  }

  public static EmailMessageThemeIdentifier parse(String s) {
    Objects.requireNonNull(s, "s must not be null");
    String[] parts = s.split(":", EXPECTED_PARTS);
    if (parts.length != EXPECTED_PARTS) {
      throw new IllegalArgumentException("invalid theme identifier: " + s);
    }

    return EmailMessageThemeIdentifier.of(parts[0], parts[1]);
  }
}

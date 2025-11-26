package com.sitepark.ies.sharedkernel.email;

import com.sitepark.ies.sharedkernel.security.UserAuthentication.Builder;
import java.util.Objects;
import javax.annotation.concurrent.Immutable;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an email address with optional display name.
 *
 * <p>This immutable value object encapsulates an email address and an
 * optional human-readable name. Used for sender and recipient information
 * in {@link Email}.
 *
 * <p>Example usage:
 * <pre>{@code
 * EmailAddress address = EmailAddress.builder()
 *     .address("user@example.com")
 *     .name("Max Mustermann")
 *     .build();
 * }</pre>
 */
@SuppressWarnings({"PMD.AvoidFieldNameMatchingMethodName"})
@Immutable
public final class EmailAddress {

  @NotNull private final String address;
  private final String name;

  private EmailAddress(Builder builder) {
    this.address = builder.address;
    this.name = builder.name;
  }

  public String address() {
    return this.address;
  }

  public String name() {
    return this.name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, name);
  }

  @Override
  public boolean equals(Object obj) {
    return (obj instanceof EmailAddress that)
        && Objects.equals(this.address, that.address)
        && Objects.equals(this.name, that.name);
  }

  @Override
  public String toString() {
    return "EmailAddress{" + "address='" + address + '\'' + ", name='" + name + '\'' + '}';
  }

  public Builder toBuilder() {
    return new Builder(this);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String address;
    private String name;

    private Builder() {}

    private Builder(EmailAddress emailAddress) {
      this.address = emailAddress.address;
      this.name = emailAddress.name;
    }

    public Builder address(String address) {
      Objects.requireNonNull(address, "address must not be null");
      this.address = address;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public EmailAddress build() {
      Objects.requireNonNull(address, "address must not be null");
      return new EmailAddress(this);
    }
  }
}

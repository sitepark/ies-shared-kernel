package com.sitepark.ies.sharedkernel.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"PMD.AvoidFieldNameMatchingMethodName", "PMD.TooManyMethods"})
@JsonDeserialize(builder = User.Builder.class)
public final class User {

  @NotNull private final String id;
  @NotNull private final String username;
  private final String firstName;
  @NotNull private final String lastName;
  private final String email;
  private final List<AuthMethod> authMethods;
  private final List<AuthFactor> authFactors;

  private User(Builder builder) {

    this.id = builder.id;
    this.username = builder.username;
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.email = builder.email;
    this.authMethods = List.copyOf(builder.authMethods);
    this.authFactors = List.copyOf(builder.authFactors);

    Objects.requireNonNull(this.id, "id cannot be null");
    Objects.requireNonNull(this.username, "username cannot be null");
    Objects.requireNonNull(this.lastName, "lastName cannot be null");
    if (this.lastName.isBlank()) {
      throw new IllegalArgumentException("lastName cannot be blank");
    }
  }

  @JsonProperty
  public String id() {
    return id;
  }

  @JsonProperty
  public String username() {
    return username;
  }

  @JsonProperty
  public String firstName() {
    return firstName;
  }

  @JsonProperty
  public String lastName() {
    return lastName;
  }

  @JsonProperty
  public String email() {
    return email;
  }

  @JsonProperty
  public List<AuthMethod> authMethods() {
    return List.copyOf(authMethods);
  }

  @JsonProperty
  public List<AuthFactor> authFactors() {
    return List.copyOf(authFactors);
  }

  @JsonIgnore
  public String getName() {
    StringBuilder name = new StringBuilder();
    if (firstName != null && !firstName.isBlank()) {
      name.append(firstName);
    }
    if (!name.isEmpty()) {
      name.append(' ');
    }
    name.append(lastName);
    return name.toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, firstName, lastName, email, authMethods, authFactors);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof User that)) {
      return false;
    }
    return Objects.equals(this.id, that.id)
        && Objects.equals(this.username, that.username)
        && Objects.equals(this.firstName, that.firstName)
        && Objects.equals(this.lastName, that.lastName)
        && Objects.equals(this.email, that.email)
        && Objects.equals(this.authMethods, that.authMethods)
        && Objects.equals(this.authFactors, that.authFactors);
  }

  @Override
  public String toString() {
    return "User{"
        + "id='"
        + id
        + '\''
        + ", username='"
        + username
        + '\''
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", email='"
        + email
        + '\''
        + ", authMethods="
        + authMethods
        + ", authFactors="
        + authFactors
        + '}';
  }

  public static Builder builder() {
    return new Builder();
  }

  public Builder toBuilder() {
    return new Builder(this);
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class Builder {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private final List<AuthMethod> authMethods = new ArrayList<>();
    private final List<AuthFactor> authFactors = new ArrayList<>();

    private Builder() {}

    private Builder(User user) {

      this.id = user.id;
      this.username = user.username;
      this.firstName = user.firstName;
      this.lastName = user.lastName;
      this.email = user.email;
      this.authMethods.addAll(user.authMethods);
      this.authFactors.addAll(user.authFactors);
    }

    public Builder id(String id) {
      Objects.requireNonNull(id, "id cannot be null");
      this.id = id;
      return this;
    }

    public Builder username(String username) {
      Objects.requireNonNull(username, "username cannot be null");
      this.username = username;
      return this;
    }

    public Builder firstName(String firstName) {
      Objects.requireNonNull(firstName, "firstName cannot be null");
      this.firstName = firstName;
      return this;
    }

    public Builder lastName(String lastName) {
      Objects.requireNonNull(lastName, "lastName cannot be null");
      this.lastName = lastName;
      return this;
    }

    public Builder email(String email) {
      Objects.requireNonNull(email, "email cannot be null");
      this.email = email;
      return this;
    }

    public Builder authMethods(AuthMethod... authMethods) {
      Objects.requireNonNull(authMethods, "authMethods cannot be null");
      for (AuthMethod authMethod : authMethods) {
        this.authMethod(authMethod);
      }
      return this;
    }

    public Builder authMethod(AuthMethod authMethod) {
      Objects.requireNonNull(authMethod, "authMethod cannot be null");
      this.authMethods.add(authMethod);
      return this;
    }

    public Builder authFactors(AuthFactor... authFactors) {
      Objects.requireNonNull(authFactors, "authFactors cannot be null");
      for (AuthFactor authFactor : authFactors) {
        this.authFactor(authFactor);
      }
      return this;
    }

    public Builder authFactor(AuthFactor authFactor) {
      Objects.requireNonNull(authFactor, "authFactor cannot be null");
      this.authFactors.add(authFactor);
      return this;
    }

    public User build() {
      return new User(this);
    }
  }
}

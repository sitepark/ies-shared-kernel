package com.sitepark.ies.sharedkernel.security;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.sitepark.ies.sharedkernel.base.ListBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"PMD.AvoidFieldNameMatchingMethodName", "PMD.LawOfDemeter"})
public final class UserAuthentication implements Authentication {

  @NotNull private final User user;

  @NotNull private final List<Permission> permissions;

  private UserAuthentication(Builder builder) {
    Objects.requireNonNull(builder.user, "user must not be null");
    this.user = builder.user;
    this.permissions = List.copyOf(builder.permissions);
  }

  public User user() {
    return user;
  }

  @Override
  public String name() {
    return user.getName();
  }

  @Override
  public List<Permission> permissions() {
    return List.copyOf(this.permissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, permissions);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof UserAuthentication that)) {
      return false;
    }
    return Objects.equals(this.user, that.user)
        && Objects.equals(this.permissions, that.permissions);
  }

  @Override
  public String toString() {
    return "UserAuthentication{" + "user=" + user + ", permissions=" + permissions + '}';
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private User user;

    private final List<Permission> permissions = new ArrayList<>();

    private Builder() {}

    public Builder user(User user) {
      Objects.requireNonNull(user, "user must not be null");
      this.user = user;
      return this;
    }

    public Builder permissions(Consumer<ListBuilder<Permission>> configurer) {
      ListBuilder<Permission> listBuilder = new ListBuilder<>();
      configurer.accept(listBuilder);
      this.permissions.clear();
      this.permissions.addAll(listBuilder.build());
      return this;
    }

    @JsonSetter
    public Builder permissions(List<Permission> permissions) {
      return this.permissions(list -> list.addAll(permissions));
    }

    public UserAuthentication build() {
      return new UserAuthentication(this);
    }
  }
}

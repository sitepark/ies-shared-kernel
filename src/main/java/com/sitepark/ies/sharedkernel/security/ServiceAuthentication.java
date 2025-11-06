package com.sitepark.ies.sharedkernel.security;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.sitepark.ies.sharedkernel.base.ListBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"PMD.AvoidFieldNameMatchingMethodName"})
public final class ServiceAuthentication implements Authentication {

  @NotNull private final String name;

  @NotNull private final List<Permission> permissions;

  private ServiceAuthentication(Builder builder) {
    Objects.requireNonNull(builder.name, "name must not be null");
    this.name = builder.name;
    this.permissions = List.copyOf(builder.permissions);
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public List<Permission> permissions() {
    return List.copyOf(this.permissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, permissions);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ServiceAuthentication that)) {
      return false;
    }
    return Objects.equals(this.name, that.name)
        && Objects.equals(this.permissions, that.permissions);
  }

  @Override
  public String toString() {
    return "ServiceAuthentication{" + "name=" + name + ", permissions=" + permissions + '}';
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private String name;

    private final List<Permission> permissions = new ArrayList<>();

    private Builder() {}

    public Builder name(String name) {
      Objects.requireNonNull(name, "name must not be null");
      this.name = name;
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

    public ServiceAuthentication build() {
      return new ServiceAuthentication(this);
    }
  }
}

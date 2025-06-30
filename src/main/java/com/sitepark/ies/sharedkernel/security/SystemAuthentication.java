package com.sitepark.ies.sharedkernel.security;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
public class SystemAuthentication implements Authentication {

  private final String purpose;

  private final List<Permission> permissions;

  public SystemAuthentication(String purpose, Permission... permissions) {
    this.purpose = purpose;
    this.permissions = List.copyOf(Arrays.asList(permissions));
  }

  @Override
  public String name() {
    return "System";
  }

  @Override
  public String purpose() {
    return this.purpose;
  }

  @Override
  public List<Permission> permissions() {
    return this.permissions;
  }
}

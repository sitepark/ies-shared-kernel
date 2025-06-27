package com.sitepark.ies.sharedkernel.security;

import java.util.Arrays;
import java.util.List;

public class SystemAuthentication implements Authentication {

  private final String purpose;

  private final List<Permission> permissions;

  public SystemAuthentication(String purpose, Permission... permissions) {
    this.purpose = purpose;
    this.permissions = List.copyOf(Arrays.asList(permissions));
  }

  @Override
  public String getName() {
    return "System";
  }

  @Override
  public String getPurpose() {
    return this.purpose;
  }

  @Override
  public List<Permission> getPermissions() {
    return this.permissions;
  }
}

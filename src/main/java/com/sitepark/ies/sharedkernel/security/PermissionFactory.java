package com.sitepark.ies.sharedkernel.security;

@SuppressWarnings("PMD.ImplicitFunctionalInterface")
public interface PermissionFactory {

  default Permission createPermission(String permissionType) {
    return this.createPermission(permissionType, null);
  }

  Permission createPermission(String permissionType, String permissionData);
}

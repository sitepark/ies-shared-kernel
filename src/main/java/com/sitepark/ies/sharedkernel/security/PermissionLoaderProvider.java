package com.sitepark.ies.sharedkernel.security;

import java.util.List;

@SuppressWarnings("PMD.ImplicitFunctionalInterface")
public interface PermissionLoaderProvider {
  List<Permission> loadByUser(String id);
}

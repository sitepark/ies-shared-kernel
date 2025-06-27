package com.sitepark.ies.sharedkernel.security;

import java.util.Map;

@SuppressWarnings("PMD.ImplicitFunctionalInterface")
public interface PermissionTypesProvider {
  Map<String, Class<? extends Permission>> getPermissionTypes();
}

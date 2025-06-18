package com.sitepark.ies.sharedkernel.security;

import java.util.Map;

public interface PermissionTypesProvider {
  Map<String, Class<? extends Permission>> getPermissionTypes();
}

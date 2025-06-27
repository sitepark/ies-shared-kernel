package com.sitepark.ies.sharedkernel.security;

import java.util.List;

public interface PermissionLoaderProvider {
  List<Permission> loadByUser(String id);
}

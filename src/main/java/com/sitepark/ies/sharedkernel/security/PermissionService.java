package com.sitepark.ies.sharedkernel.security;

import java.util.List;

public interface PermissionService {

  /**
   * @throws PermissionParseException Thrown when the permission data could not be parsed.
   * @throws PermissionCreateException Thrown when the permission could not be created.
   */
  default Permission createPermission(String permissionType) {
    return this.createPermission(permissionType, null);
  }

  /**
   * @throws PermissionParseException Thrown when the permission data could not be parsed.
   * @throws PermissionCreateException Thrown when the permission could not be created.
   */
  Permission createPermission(String permissionType, String permissionData);

  /**
   * Expects a JSON array of the form: <code>
   *   [
   *    {
   *      "type" : "TYPE",
   *      "data" : { ... }
   *    }
   *   ]
   * </code>
   *
   * @throws PermissionParseException Thrown when the permission data could not be parsed.
   * @throws PermissionCreateException Thrown when the permission could not be created.
   */
  List<Permission> createPermissions(String permissions);

  /**
   * @throws PermissionSerializeException Thrown when the permission data could not be serialized.
   */
  String serializePermissions(List<Permission> permissions);

  /**
   * @throws PermissionSerializeException Thrown when the permission data could not be serialized.
   */
  String serializePermission(Permission permission);
}

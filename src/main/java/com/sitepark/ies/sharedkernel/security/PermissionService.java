package com.sitepark.ies.sharedkernel.security;

import java.util.List;
import java.util.Map;

public interface PermissionService {

  /**
   * Creates a permission from the given raw attribute map.
   *
   * @throws PermissionCreateException Thrown when the permission could not be created.
   */
  Permission createPermission(Map<String, Object> permission);

  /**
   * Serializes the given permissions into their string representation.
   *
   * @throws PermissionSerializeException Thrown when the permission could not be serialized.
   */
  String serializePermissions(List<Permission> permissions);

  /**
   * Serializes the given permission into its string representation.
   *
   * @throws PermissionSerializeException Thrown when the permission could not be serialized.
   */
  String serializePermission(Permission permission);

  /**
   * Deserializes the given data into a list of permissions.
   *
   * @throws PermissionDeserializeException Thrown when the permission could not be deserialized.
   */
  List<Permission> deserializePermissions(String data);

  /**
   * Deserializes the given data into a single permission.
   *
   * @throws PermissionDeserializeException Thrown when the permission could not be deserialized.
   */
  Permission deserializePermission(String data);
}

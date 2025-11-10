package com.sitepark.ies.sharedkernel.security;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

public interface PermissionService {

  /**
   * @throws PermissionCreateException Thrown when the permission could not be created.
   */
  Permission createPermission(JsonNode permissionData);

  /**
   * @throws PermissionSerializeException Thrown when the permission could not be serialized.
   */
  String serializePermissions(List<Permission> permissions);

  /**
   * @throws PermissionSerializeException Thrown when the permission could not be serialized.
   */
  String serializePermission(Permission permission);

  /**
   * @throws PermissionDeserializeException Thrown when the permission could not be deserialized.
   */
  List<Permission> deserializePermissions(String data);

  /**
   * @throws PermissionDeserializeException Thrown when the permission could not be deserialized.
   */
  Permission deserializePermission(String data);
}

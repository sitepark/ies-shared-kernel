package com.sitepark.ies.sharedkernel.security;

import java.util.List;
import java.util.Optional;

public interface Authentication {

  /** Name of the subject for which this authentication applies. */
  String name();

  /** Specifies the purpose for which the authentication applies. */
  String purpose();

  /** Returns all permissions that were obtained via this authentication. */
  List<Permission> permissions();

  /** Checks whether the specified authorization is included via this authentication. */
  default boolean hasPermission(Class<? extends Permission> type) {

    for (Permission permission : this.permissions()) {
      if (FullAccess.class.equals(permission.getClass())) {
        return true;
      }
      if (type.equals(permission.getClass())) {
        return true;
      }
    }

    return false;
  }

  default boolean hasFullAccess() {
    return this.hasPermission(FullAccess.class);
  }

  /** Returns a specific permission if that is included. */
  @SuppressWarnings("unchecked")
  default <T extends Permission> Optional<T> getPermission(Class<T> type) {

    for (Permission permission : this.permissions()) {
      if (type.isInstance(permission)) {
        return Optional.of((T) permission);
      }
    }

    return Optional.empty();
  }

  default <T extends Permission> List<T> permissions(Class<T> type) {
    return this.permissions().stream().filter(type::isInstance).map(type::cast).toList();
  }
}

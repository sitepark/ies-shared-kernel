package com.sitepark.ies.sharedkernel.security;

import java.util.Optional;

public interface UserServiceProvider {

  Optional<User> findByUsername(String username);

  Optional<User> findById(String id);

  void upgradePasswordHash(String userId, String newPassword);
}

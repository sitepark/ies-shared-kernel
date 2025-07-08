package com.sitepark.ies.sharedkernel.security;

import java.util.Optional;

public interface UserServiceProvider {

  Optional<User> findByUsername(String username);

  Optional<User> findById(String id);

  Optional<String> getPasswordHash(String userId);

  void upgradePasswordHash(String userId, String newPassword);

  /**
   * Users who log in via external systems may not yet have been created in this system or their
   * data may be out of date. This method synchronizes the user who has just logged in with the data
   * from the external system. This is the case, for example, when logging in via OAuth2.
   */
  Optional<User> syncAuthenticatedUser(AuthenticationContext context, User user);
}

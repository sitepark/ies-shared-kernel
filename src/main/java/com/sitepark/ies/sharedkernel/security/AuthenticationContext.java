package com.sitepark.ies.sharedkernel.security;

import org.jspecify.annotations.Nullable;

/**
 * Describes how a user was authenticated.
 *
 * @param providerId the id of the configured provider, {@code null} if the provider type has no
 *     configurable instances (e.g. OIDC logins)
 */
public record AuthenticationContext(
    AuthProviderType providerType,
    @Nullable String providerId,
    AuthMethod method,
    String externalId) {}

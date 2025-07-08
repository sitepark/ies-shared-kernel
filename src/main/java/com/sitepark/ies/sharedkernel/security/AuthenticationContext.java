package com.sitepark.ies.sharedkernel.security;

public record AuthenticationContext(
    AuthProviderType providerType, String providerId, AuthMethod method, String externalId) {}

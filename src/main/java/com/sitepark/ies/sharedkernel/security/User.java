package com.sitepark.ies.sharedkernel.security;

public record User(
    String id,
    String username,
    String firstName,
    String lastName,
    String email,
    AuthMethod[] authMethods,
    AuthFactor[] authFactors,
    String passwordHash) {

  public User(
      String id,
      String username,
      String firstName,
      String lastName,
      String email,
      AuthMethod[] authMethods,
      AuthFactor[] authFactors,
      String passwordHash) {
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.authMethods = authMethods != null ? authMethods : new AuthMethod[] {};
    this.authFactors = authFactors != null ? authFactors : new AuthFactor[] {};
    this.passwordHash = passwordHash;
  }

  @Override
  public AuthMethod[] authMethods() {
    return authMethods.clone();
  }

  @Override
  public AuthFactor[] authFactors() {
    return authFactors.clone();
  }

  public String getName() {
    StringBuilder name = new StringBuilder();
    if (firstName != null && !firstName.trim().isBlank()) {
      name.append(firstName.trim());
    }
    if (!name.isEmpty() && lastName != null && !lastName.trim().isBlank()) {
      name.append(' ');
    }
    if (lastName != null && !lastName.trim().isBlank()) {
      name.append(lastName.trim());
    }
    return name.toString();
  }
}

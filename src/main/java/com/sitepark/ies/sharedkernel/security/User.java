package com.sitepark.ies.sharedkernel.security;

@SuppressWarnings("PMD.DataClass")
public class User {
  private final String id;
  private final String username;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final AuthMethod[] authMethods;
  private final AuthFactor[] authFactors;
  private final String passwordHash;

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

  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public AuthMethod[] getAuthMethods() {
    return authMethods.clone();
  }

  public AuthFactor[] getAuthFactors() {
    return authFactors.clone();
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public String getName() {
    StringBuilder name = new StringBuilder();
    if (firstName != null && !firstName.isEmpty()) {
      name.append(firstName).append(' ');
    }
    name.append(lastName);
    return name.toString();
  }
}

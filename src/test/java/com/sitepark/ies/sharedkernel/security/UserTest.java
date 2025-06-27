package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

  private User user;

  @BeforeEach
  void setUp() {
    this.user =
        new User(
            "1",
            "testUser",
            "Test",
            "User",
            "test@test.com",
            new AuthMethod[] {AuthMethod.PASSWORD},
            new AuthFactor[] {AuthFactor.TOTP},
            "passwordHash");
  }

  @Test
  @SuppressWarnings("PMD.NullAssignment")
  void testGetAuthMethods() {
    this.user.authMethods()[0] = null;

    assertArrayEquals(
        new AuthMethod[] {AuthMethod.PASSWORD},
        this.user.authMethods(),
        "authMethods should return a copy of the original array");
  }

  @Test
  void testGetName() {
    assertEquals(
        "Test User",
        this.user.getName(),
        "getName should return 'Test User' for first and last name");
  }

  @Test
  void testGetNameWithBlankFirstName() {
    User user =
        new User(
            "1",
            "testuser",
            " ",
            "User",
            "test@test.com",
            new AuthMethod[] {AuthMethod.PASSWORD},
            new AuthFactor[] {AuthFactor.TOTP},
            "passwordHash");
    assertEquals("User", user.getName(), "getName should return 'User' when first name is blank");
  }

  @Test
  void testGetNameWithNullFirstName() {
    User user =
        new User(
            "1",
            "testuser",
            null,
            "User",
            "test@test.com",
            new AuthMethod[] {AuthMethod.PASSWORD},
            new AuthFactor[] {AuthFactor.TOTP},
            "passwordHash");
    assertEquals("User", user.getName(), "getName should return 'User' when first name is blank");
  }

  @Test
  void testGetNameWithBlankLastName() {
    User user =
        new User(
            "1",
            "testuser",
            "Test",
            " ",
            "test@test.com",
            new AuthMethod[] {AuthMethod.PASSWORD},
            new AuthFactor[] {AuthFactor.TOTP},
            "passwordHash");
    assertEquals("Test", user.getName(), "getName should return 'User' when first name is blank");
  }

  @Test
  void testGetNameWithNullLastName() {
    User user =
        new User(
            "1",
            "testuser",
            "Test",
            null,
            "test@test.com",
            new AuthMethod[] {AuthMethod.PASSWORD},
            new AuthFactor[] {AuthFactor.TOTP},
            "passwordHash");
    assertEquals("Test", user.getName(), "getName should return 'User' when first name is blank");
  }

  @Test
  void testNullAuthMethod() {
    User user =
        new User(
            "1",
            "testuser",
            "Test",
            " ",
            "test@test.com",
            null,
            new AuthFactor[] {AuthFactor.TOTP},
            "passwordHash");
    assertArrayEquals(
        new AuthMethod[] {}, user.authMethods(), "authMethods should be empty when null");
  }

  @Test
  void testNullAuthFactor() {
    User user =
        new User(
            "1",
            "testuser",
            "Test",
            " ",
            "test@test.com",
            new AuthMethod[] {AuthMethod.PASSWORD},
            null,
            "passwordHash");
    assertArrayEquals(
        new AuthFactor[] {}, user.authFactors(), "authFactors should be empty when null");
  }

  @Test
  @SuppressWarnings("PMD.NullAssignment")
  void testGetAuthFactor() {

    this.user.authFactors()[0] = null;

    assertArrayEquals(
        new AuthFactor[] {AuthFactor.TOTP},
        this.user.authFactors(),
        "authFactors should return a copy of the original array");
  }
}

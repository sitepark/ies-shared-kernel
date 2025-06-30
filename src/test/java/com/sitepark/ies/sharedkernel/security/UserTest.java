package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.*;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

  private User user;

  @BeforeEach
  void setUp() {
    this.user =
        User.builder()
            .id("1")
            .username("testUser")
            .firstName("Test")
            .lastName("User")
            .email("test@test.com")
            .authMethods(AuthMethod.PASSWORD)
            .authFactors(AuthFactor.TOTP)
            .passwordHash("passwordHash")
            .build();
  }

  @Test
  void testEquals() {
    EqualsVerifier.forClass(User.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(User.class).verify();
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
        User.builder()
            .id("1")
            .username("testUser")
            .firstName(" ")
            .lastName("User")
            .email("test@test.com")
            .authMethods(AuthMethod.PASSWORD)
            .authFactors(AuthFactor.TOTP)
            .passwordHash("passwordHash")
            .build();

    assertEquals("User", user.getName(), "getName should return 'User' when first name is blank");
  }

  @Test
  void testGetNameWithNullFirstName() {
    User user =
        User.builder()
            .id("1")
            .username("testUser")
            .lastName("User")
            .email("test@test.com")
            .authMethods(AuthMethod.PASSWORD)
            .authFactors(AuthFactor.TOTP)
            .passwordHash("passwordHash")
            .build();
    assertEquals("User", user.getName(), "getName should return 'User' when first name is blank");
  }

  @Test
  void testWithNullLastName() {
    assertThrows(
        NullPointerException.class,
        () ->
            User.builder()
                .id("1")
                .username("testUser")
                .firstName("Test")
                .email("test@test.com")
                .authMethods(AuthMethod.PASSWORD)
                .authFactors(AuthFactor.TOTP)
                .passwordHash("passwordHash")
                .build());
  }

  @Test
  void testWithBlankLastName() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            User.builder()
                .id("1")
                .username("testUser")
                .firstName("Test")
                .lastName(" ")
                .email("test@test.com")
                .authMethods(AuthMethod.PASSWORD)
                .authFactors(AuthFactor.TOTP)
                .passwordHash("passwordHash")
                .build());
  }

  @Test
  void testNullAuthMethod() {
    assertThrows(NullPointerException.class, () -> User.builder().authMethods((AuthMethod) null));
  }

  @Test
  void testNullAuthFactor() {
    assertThrows(NullPointerException.class, () -> User.builder().authFactors((AuthFactor) null));
  }

  @Test
  void testGetAuthFactor() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> this.user.authFactors().add(null),
        "authFactors should return a copy of the original");
  }

  @Test
  void testGetAuthMethods() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> this.user.authMethods().add(null),
        "authFactors should return a copy of the original");
  }

  @Test
  void testToBuilder() {
    User user =
        User.builder()
            .id("1")
            .username("testUser")
            .lastName("User")
            .email("test@test.com")
            .authMethods(AuthMethod.PASSWORD)
            .authFactors(AuthFactor.TOTP)
            .passwordHash("passwordHash")
            .build()
            .toBuilder()
            .email("test2@test.com")
            .build();

    User expectedUser =
        User.builder()
            .id("1")
            .username("testUser")
            .lastName("User")
            .email("test2@test.com")
            .authMethods(AuthMethod.PASSWORD)
            .authFactors(AuthFactor.TOTP)
            .passwordHash("passwordHash")
            .build();

    assertEquals(expectedUser, user, "toBuilder should return the same object");
  }
}

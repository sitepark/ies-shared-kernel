package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            .roles("ROLE_USER")
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
            .identity(Identity.internal())
            .authMethods(AuthMethod.PASSWORD)
            .authFactors(AuthFactor.TOTP)
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
            .identity(Identity.internal())
            .authMethods(AuthMethod.PASSWORD)
            .authFactors(AuthFactor.TOTP)
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
  void testGetRoles() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> this.user.roles().add(null),
        "roles should return a copy of the original");
  }

  @Test
  void testToBuilder() {
    User user =
        User.builder()
            .id("1")
            .username("testUser")
            .lastName("User")
            .email("test@test.com")
            .identity(Identity.internal())
            .authMethods(AuthMethod.PASSWORD)
            .authFactors(AuthFactor.TOTP)
            .roles("ROLE_USER")
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
            .identity(Identity.internal())
            .authMethods(AuthMethod.PASSWORD)
            .authFactors(AuthFactor.TOTP)
            .roles("ROLE_USER")
            .build();

    assertEquals(expectedUser, user, "toBuilder should return the same object");
  }

  @Test
  void testSerialize() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(this.user);

    String expected =
        """
        {"id":"1","username":"testUser","firstName":"Test","lastName":"User","email":"test@test.com","identity":{"@type":"internal"},"authMethods":["PASSWORD"],"authFactors":["TOTP"],"roles":["ROLE_USER"]}""";

    assertEquals(expected, json, "Serialized JSON should match expected format");
  }

  @Test
  void testDeserialize() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    String json =
        """
        {"id":"1","username":"testUser","firstName":"Test","lastName":"User","email":"test@test.com","identity":{"@type":"internal"},"authMethods":["PASSWORD"],"authFactors":["TOTP"],"roles":["ROLE_USER"]}""";
    User user = mapper.readValue(json, User.class);

    assertEquals(this.user, user, "Deserialized User should match original User");
  }
}

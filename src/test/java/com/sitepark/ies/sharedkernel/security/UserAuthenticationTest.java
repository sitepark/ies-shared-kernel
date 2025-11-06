package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.*;

import com.jparams.verifier.tostring.ToStringVerifier;
import java.util.List;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserAuthenticationTest {

  private User user;

  @BeforeEach
  void setUp() {
    this.user =
        User.builder().id("1").username("peterpan").firstName("Peter").lastName("Pan").build();
  }

  @Test
  void testEquals() {
    EqualsVerifier.forClass(UserAuthentication.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(UserAuthentication.class).verify();
  }

  @Test
  void testUser() {
    UserAuthentication authentication = UserAuthentication.builder().user(this.user).build();
    assertEquals(this.user, authentication.user(), "User should match the one set in builder");
  }

  @Test
  void testUserWithNull() {
    assertThrows(
        NullPointerException.class,
        () -> UserAuthentication.builder().user(null),
        "User should not be null");
  }

  @Test
  void testPermissions() {
    UserAuthentication authentication =
        UserAuthentication.builder().user(this.user).permissions(List.of(new FullAccess())).build();
    assertEquals(
        List.of(new FullAccess()),
        authentication.permissions(),
        "Permissions should match the ones set in builder");
  }

  @Test
  void testName() {
    UserAuthentication authentication = UserAuthentication.builder().user(this.user).build();
    assertEquals("Peter Pan", authentication.name(), "Name should be derived from user");
  }
}

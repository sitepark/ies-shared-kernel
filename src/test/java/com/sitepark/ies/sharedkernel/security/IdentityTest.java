package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IdentityTest {
  @Test
  void testInternal() {
    assertInstanceOf(InternalIdentity.class, Identity.internal());
  }

  @Test
  void testLdap() {
    assertInstanceOf(
        LdapIdentity.class, Identity.ldap("1", "cn=Test User,ou=users,dc=example,dc=com"));
  }
}

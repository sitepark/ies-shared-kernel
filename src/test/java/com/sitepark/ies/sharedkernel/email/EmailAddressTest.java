package com.sitepark.ies.sharedkernel.email;

import static org.junit.jupiter.api.Assertions.*;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class EmailAddressTest {

  @Test
  void testEquals() {
    EqualsVerifier.forClass(EmailAddress.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(EmailAddress.class).verify();
  }

  @Test
  void testName() {
    EmailAddress address = EmailAddress.builder().name("test").address("test@test.com").build();
    assertEquals("test", address.name(), "unexpected name");
  }

  @Test
  void testAddress() {
    EmailAddress address = EmailAddress.builder().address("test@test.com").build();
    assertEquals("test@test.com", address.address(), "unexpected address");
  }

  @Test
  void testSetNullAddress() {
    assertThrows(
        NullPointerException.class,
        () -> EmailAddress.builder().address(null),
        "address should not be null");
  }

  @Test
  void testToBuilder() {
    EmailAddress address = EmailAddress.builder().name("test").address("test@test.com").build();

    EmailAddress copy = address.toBuilder().name("test2").build();
    EmailAddress exprected = EmailAddress.builder().name("test2").address("test@test.com").build();

    assertEquals(exprected, copy, "toBuilder should return a copy of the object");
  }
}

package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.*;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class FullAccessTest {

  @Test
  void testEquals() {
    EqualsVerifier.forClass(FullAccess.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(FullAccess.class).verify();
  }

  @Test
  void testGetType() {
    FullAccess fullAccess = new FullAccess();
    assertEquals("FULL_ACCESS", fullAccess.getType(), "Wrong type");
  }
}

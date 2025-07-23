package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.*;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class InternalIdentityTest {

  @Test
  void testEquals() {
    EqualsVerifier.forClass(InternalIdentity.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(InternalIdentity.class).verify();
  }
}

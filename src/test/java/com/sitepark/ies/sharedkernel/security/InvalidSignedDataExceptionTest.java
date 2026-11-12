package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class InvalidSignedDataExceptionTest {

  @Test
  void testMessage() {
    assertEquals(
        "invalid", new InvalidSignedDataException("invalid").getMessage(), "unexpected message");
  }

  @Test
  void testCause() {
    Throwable cause = new IllegalStateException();
    assertSame(
        cause, new InvalidSignedDataException("invalid", cause).getCause(), "unexpected cause");
  }
}

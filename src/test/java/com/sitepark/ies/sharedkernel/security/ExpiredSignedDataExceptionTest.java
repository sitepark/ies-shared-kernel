package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class ExpiredSignedDataExceptionTest {

  @Test
  void testIsInvalidSignedDataException() {
    assertInstanceOf(
        InvalidSignedDataException.class,
        new ExpiredSignedDataException("expired"),
        "an expired token is also an invalid one");
  }

  @Test
  void testMessage() {
    assertEquals(
        "expired", new ExpiredSignedDataException("expired").getMessage(), "unexpected message");
  }

  @Test
  void testCause() {
    Throwable cause = new IllegalStateException();
    assertSame(
        cause, new ExpiredSignedDataException("expired", cause).getCause(), "unexpected cause");
  }
}

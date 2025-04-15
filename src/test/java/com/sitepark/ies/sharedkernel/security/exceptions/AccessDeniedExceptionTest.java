package com.sitepark.ies.sharedkernel.security.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AccessDeniedExceptionTest {
  @Test
  void testConstructor() {
    AccessDeniedException exception = new AccessDeniedException("Test");
    assertEquals("Test", exception.getMessage(), "Wrong message");
  }

  @Test
  void testConstructorWithCause() {
    AccessDeniedException exception =
        new AccessDeniedException("Test", new Exception("CauseMessage"));
    assertEquals("CauseMessage", exception.getCause().getMessage(), "Wrong cause message");
  }
}

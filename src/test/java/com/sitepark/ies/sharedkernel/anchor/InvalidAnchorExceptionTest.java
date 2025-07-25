package com.sitepark.ies.sharedkernel.anchor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InvalidAnchorExceptionTest {
  @Test
  void testGetMessageWithNullMessage() {
    InvalidAnchorException e = new InvalidAnchorException("anchor", null);
    assertEquals("Invalid anchor 'anchor'", e.getMessage(), "Unexpected message");
  }

  @Test
  void testName() {
    InvalidAnchorException e = new InvalidAnchorException("anchor", null);
    assertEquals("anchor", e.getName(), "Unexpected name");
  }
}

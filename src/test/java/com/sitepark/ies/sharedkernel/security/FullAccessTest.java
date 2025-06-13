package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FullAccessTest {
  @Test
  void testGetType() {
    FullAccess fullAccess = new FullAccess();
    assertEquals("FULL_ACCESS", fullAccess.getType(), "Wrong type");
  }
}

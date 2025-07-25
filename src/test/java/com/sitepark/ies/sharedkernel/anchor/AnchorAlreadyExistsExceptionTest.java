package com.sitepark.ies.sharedkernel.anchor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AnchorAlreadyExistsExceptionTest {
  @Test
  void testMessage() {
    AnchorAlreadyExistsException exception =
        new AnchorAlreadyExistsException(Anchor.ofString("anchor"), "123");
    assertEquals(
        "Anchor anchor already exists for id 123", exception.getMessage(), "unexpected message");
  }

  @Test
  void testAnchor() {
    AnchorAlreadyExistsException exception =
        new AnchorAlreadyExistsException(Anchor.ofString("anchor"), "123");
    assertEquals(Anchor.ofString("anchor"), exception.getAnchor(), "unexpected anchor");
  }

  @Test
  void testOwner() {
    AnchorAlreadyExistsException exception =
        new AnchorAlreadyExistsException(Anchor.ofString("anchor"), "123");
    assertEquals("123", exception.getId(), "unexpected owner");
  }
}

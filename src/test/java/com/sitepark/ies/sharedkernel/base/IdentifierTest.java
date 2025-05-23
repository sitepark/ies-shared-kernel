package com.sitepark.ies.sharedkernel.base;

import static org.junit.jupiter.api.Assertions.*;

import com.sitepark.ies.sharedkernel.anchor.domain.Anchor;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Optional;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

@SuppressFBWarnings({
  "PI_DO_NOT_REUSE_PUBLIC_IDENTIFIERS_CLASS_NAMES",
  "NP_NULL_PARAM_DEREF_NONVIRTUAL"
})
class IdentifierTest {

  @Test
  @SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
  void testEquals() {
    EqualsVerifier.forClass(Identifier.class).verify();
  }

  @Test
  void testToStringWithId() {
    Identifier identifier = Identifier.ofString("123");
    assertEquals("123", identifier.toString(), "unexpected identifier");
  }

  @Test
  void testToStringWithAnchor() {
    Identifier identifier = Identifier.ofString("abc");
    assertEquals("abc", identifier.toString(), "unexpected identifier");
  }

  @Test
  void testOfStringToId() {
    Identifier identifier = Identifier.ofString("123");
    assertEquals(Optional.of("123"), identifier.getId(), "id expected");
  }

  @Test
  void testOfStringToAnchor() {
    Identifier identifier = Identifier.ofString("abc");
    Anchor anchor = Anchor.ofString("abc");
    assertEquals(Optional.of(anchor), identifier.getAnchor(), "anchor expected");
  }

  @Test
  void testOfStringWithLongString() {
    Identifier identifier = Identifier.ofString("abcdefghijklmnopqrstuvwxyz");
    Anchor anchor = Anchor.ofString("abcdefghijklmnopqrstuvwxyz");
    assertEquals(Optional.of(anchor), identifier.getAnchor(), "anchor expected");
  }

  @Test
  void testOfStringWithDot() {
    Identifier identifier = Identifier.ofString("123.b");
    Anchor anchor = Anchor.ofString("123.b");
    assertEquals(Optional.of(anchor), identifier.getAnchor(), "anchor expected");
  }

  @Test
  void testOfStringWithNull() {
    assertThrows(NullPointerException.class, () -> Identifier.ofString(null));
  }

  @Test
  void testOfStringWithBlank() {
    assertThrows(IllegalArgumentException.class, () -> Identifier.ofString(" "));
  }

  @Test
  void testOfId() {
    Identifier identifier = Identifier.ofId("123");
    assertEquals(Optional.of("123"), identifier.getId(), "id expected");
  }

  @Test
  void testOfIdWithZero() {
    assertThrows(IllegalArgumentException.class, () -> Identifier.ofId("0"));
  }

  @Test
  void testOfIdWithInvalidId() {
    assertThrows(IllegalArgumentException.class, () -> Identifier.ofId("0x"));
  }

  @Test
  void testOfAnchor() {
    Anchor anchor = Anchor.ofString("abc");
    Identifier identifier = Identifier.ofAnchor(anchor);
    assertEquals(Optional.of(Anchor.ofString("abc")), identifier.getAnchor(), "anchor expected");
  }

  @Test
  void testOfAnchorString() {
    Identifier identifier = Identifier.ofAnchor("abc");
    assertEquals(Optional.of(Anchor.ofString("abc")), identifier.getAnchor(), "anchor expected");
  }

  @Test
  void testOfEmptyAnchor() {
    assertThrows(IllegalArgumentException.class, () -> Identifier.ofAnchor(Anchor.EMPTY));
  }

  @Test
  void testOfAnchorWithNull() {
    assertThrows(NullPointerException.class, () -> Identifier.ofAnchor((Anchor) null));
  }
}

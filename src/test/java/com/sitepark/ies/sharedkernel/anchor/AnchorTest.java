package com.sitepark.ies.sharedkernel.anchor;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class AnchorTest {
  @Test
  void testEquals() {
    EqualsVerifier.forClass(Anchor.class).verify();
  }

  @Test
  void testOfNullString() {
    Anchor anchor = Anchor.ofString(null);
    assertNull(anchor, "anchor should be null");
  }

  @Test
  void testOfBlankString() {
    Anchor anchor = Anchor.ofString("  ");
    assertEquals(Anchor.EMPTY, anchor, "anchor should be Anchor.EMPTY");
  }

  @Test
  void testValidateValidAnchor() {
    assertDoesNotThrow(
        () -> {
          Anchor.ofString("123a");
        });
  }

  @Test
  void testValidateOnlyDigits() {
    assertThrows(
        InvalidAnchorException.class,
        () -> Anchor.ofString("1234556789012345"),
        "anchor must not only contain numbers");
  }

  @Test
  void testValidateInvalidChars() {
    assertThrows(
        InvalidAnchorException.class,
        () -> Anchor.ofString("a.b,c"),
        "anchor must not contain commas");
  }

  @Test
  void testToString() {
    Anchor anchor = Anchor.ofString("a.b.c");
    assertEquals("a.b.c", anchor.toString(), "unexpected string representation");
  }

  @Test
  void testEmptyAnchorToString() {
    Anchor anchor = Anchor.EMPTY;
    assertEquals("EMPTY", anchor.toString(), "unexpected string representation");
  }

  @Test
  void testSerialize() throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();

    Anchor anchor = Anchor.ofString("abc");

    String json = mapper.writeValueAsString(anchor);

    assertEquals("\"abc\"", json, "unexpected value");
  }

  @Test
  void testDeserialize() throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();

    String json = "\"abc\"";

    Anchor anchor = mapper.readValue(json, Anchor.class);

    assertEquals("abc", anchor.getName(), "unexpected anchor");
  }
}

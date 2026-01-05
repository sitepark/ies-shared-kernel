package com.sitepark.ies.sharedkernel.email;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class EmailMessageTypeIdentifierTest {

  @Test
  void testOf() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of("category", "key");
    assertNotNull(identifier, "identifier should not be null");
  }

  @Test
  void testOfCategory() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of("category", "key");
    assertEquals("category", identifier.category(), "category should match input");
  }

  @Test
  void testOfKey() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of("category", "key");
    assertEquals("key", identifier.key(), "key should match input");
  }

  @Test
  void testOfWithNullCategory() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of(null, "key");
    assertEquals(null, identifier.category(), "null category should be accepted by constructor");
  }

  @Test
  void testOfWithNullKey() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of("category", null);
    assertEquals(null, identifier.key(), "null key should be accepted by constructor");
  }

  @Test
  void testOfWithBlankCategory() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of(" ", "key");
    assertEquals(" ", identifier.category(), "blank category should be accepted by constructor");
  }

  @Test
  void testOfWithBlankKey() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of("category", " ");
    assertEquals(" ", identifier.key(), "blank key should be accepted by constructor");
  }

  @Test
  void testOfWithEmptyCategory() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of("", "key");
    assertEquals("", identifier.category(), "empty category should be accepted by constructor");
  }

  @Test
  void testOfWithEmptyKey() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of("category", "");
    assertEquals("", identifier.key(), "empty key should be accepted by constructor");
  }

  @Test
  void testOfWithSpecialCharactersInCategory() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of("cat@gory", "key");
    assertEquals(
        "cat@gory", identifier.category(), "category with special characters should be accepted");
  }

  @Test
  void testOfWithSpecialCharactersInKey() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of("category", "k#y");
    assertEquals("k#y", identifier.key(), "key with special characters should be accepted");
  }

  @Test
  void testOfWithSpacesInCategory() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of("cat egory", "key");
    assertEquals("cat egory", identifier.category(), "category with spaces should be accepted");
  }

  @Test
  void testOfWithSpacesInKey() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of("category", "k ey");
    assertEquals("k ey", identifier.key(), "key with spaces should be accepted");
  }

  @Test
  void testOfWithColonInCategory() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of("cat:egory", "key");
    assertEquals("cat:egory", identifier.category(), "category with colon should be accepted");
  }

  @Test
  void testOfWithColonInKey() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of("category", "k:ey");
    assertEquals("k:ey", identifier.key(), "key with colon should be accepted");
  }

  @Test
  void testParse() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.parse("category:key");
    EmailMessageTypeIdentifier expected = EmailMessageTypeIdentifier.of("category", "key");
    assertEquals(expected, identifier, "parsed identifier should match expected");
  }

  @Test
  void testParseWithBlankCategory() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageTypeIdentifier.parse(" :key"),
        "should throw IllegalArgumentException when parsed category is blank");
  }

  @Test
  void testParseWithBlankKey() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageTypeIdentifier.parse("category: "),
        "should throw IllegalArgumentException when parsed key is blank");
  }

  @Test
  void testParseWithEmptyCategory() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageTypeIdentifier.parse(":key"),
        "should throw IllegalArgumentException when parsed category is empty");
  }

  @Test
  void testParseWithEmptyKey() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageTypeIdentifier.parse("category:"),
        "should throw IllegalArgumentException when parsed key is empty");
  }

  @Test
  void testParserInvalidString() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageTypeIdentifier.parse("invalid"),
        "should throw IllegalArgumentException when string has no colon separator");
  }

  @Test
  void testParserNull() {
    assertThrows(
        NullPointerException.class,
        () -> EmailMessageTypeIdentifier.parse(null),
        "should throw NullPointerException when input is null");
  }

  @Test
  void testParseWithMultipleColons() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.parse("category:key:extra");
    assertEquals(
        "key:extra",
        identifier.key(),
        "key should contain everything after first colon when multiple colons present");
  }

  @Test
  void testParseWithSpecialCharactersInCategory() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.parse("cat@gory:key");
    assertEquals(
        "cat@gory",
        identifier.category(),
        "parsed category with special characters should be accepted");
  }

  @Test
  void testParseWithSpecialCharactersInKey() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.parse("category:k#y");
    assertEquals("k#y", identifier.key(), "parsed key with special characters should be accepted");
  }

  @Test
  void testToString() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of("category", "key");
    assertEquals(
        "category:key", identifier.toString(), "toString should return category:key format");
  }

  @Test
  void testToStringWithSpecialCharacters() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of("cat@gory", "k#y");
    assertEquals(
        "cat@gory:k#y", identifier.toString(), "toString should preserve special characters");
  }

  @Test
  void testToStringWithColon() {
    EmailMessageTypeIdentifier identifier = EmailMessageTypeIdentifier.of("cat:egory", "k:ey");
    assertEquals(
        "cat:egory:k:ey", identifier.toString(), "toString should preserve colons in values");
  }

  @Test
  void testEqualsContract() {
    EqualsVerifier.forClass(EmailMessageTypeIdentifier.class)
        .suppress(nl.jqno.equalsverifier.Warning.NULL_FIELDS)
        .verify();
  }
}

package com.sitepark.ies.sharedkernel.email;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class EmailMessageThemeIdentifierTest {

  @Test
  void testOf() {
    EmailMessageThemeIdentifier identifier = EmailMessageThemeIdentifier.of("category", "key");
    assertNotNull(identifier, "identifier should not be null");
  }

  @Test
  void testOfCategory() {
    EmailMessageThemeIdentifier identifier = EmailMessageThemeIdentifier.of("category", "key");
    assertEquals("category", identifier.category(), "category should match input");
  }

  @Test
  void testOfKey() {
    EmailMessageThemeIdentifier identifier = EmailMessageThemeIdentifier.of("category", "key");
    assertEquals("key", identifier.key(), "key should match input");
  }

  @Test
  void testOfWithNullCategory() {
    assertThrows(
        NullPointerException.class,
        () -> EmailMessageThemeIdentifier.of(null, "key"),
        "should throw NullPointerException when category is null");
  }

  @Test
  void testOfWithNullKey() {
    assertThrows(
        NullPointerException.class,
        () -> EmailMessageThemeIdentifier.of("category", null),
        "should throw NullPointerException when key is null");
  }

  @Test
  void testOfWithBlankCategory() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageThemeIdentifier.of(" ", "key"),
        "should throw IllegalArgumentException when category is blank");
  }

  @Test
  void testOfWithBlankKey() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageThemeIdentifier.of("category", " "),
        "should throw IllegalArgumentException when key is blank");
  }

  @Test
  void testOfWithEmptyCategory() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageThemeIdentifier.of("", "key"),
        "should throw IllegalArgumentException when category is empty");
  }

  @Test
  void testOfWithEmptyKey() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageThemeIdentifier.of("category", ""),
        "should throw IllegalArgumentException when key is empty");
  }

  @Test
  void testOfWithInvalidCharactersInCategory() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageThemeIdentifier.of("cat egory", "key"),
        "should throw IllegalArgumentException when category contains spaces");
  }

  @Test
  void testOfWithInvalidCharactersInKey() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageThemeIdentifier.of("category", "k ey"),
        "should throw IllegalArgumentException when key contains spaces");
  }

  @Test
  void testOfWithSpecialCharactersInCategory() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageThemeIdentifier.of("cat@gory", "key"),
        "should throw IllegalArgumentException when category contains special characters");
  }

  @Test
  void testOfWithSpecialCharactersInKey() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageThemeIdentifier.of("category", "k#y"),
        "should throw IllegalArgumentException when key contains special characters");
  }

  @Test
  void testOfWithValidHyphenInCategory() {
    EmailMessageThemeIdentifier identifier = EmailMessageThemeIdentifier.of("cat-egory", "key");
    assertEquals("cat-egory", identifier.category(), "category with hyphen should be accepted");
  }

  @Test
  void testOfWithValidUnderscoreInKey() {
    EmailMessageThemeIdentifier identifier = EmailMessageThemeIdentifier.of("category", "key_name");
    assertEquals("key_name", identifier.key(), "key with underscore should be accepted");
  }

  @Test
  void testOfWithValidAlphanumericCategory() {
    EmailMessageThemeIdentifier identifier = EmailMessageThemeIdentifier.of("category123", "key");
    assertEquals(
        "category123", identifier.category(), "category with alphanumeric should be accepted");
  }

  @Test
  void testOfWithValidAlphanumericKey() {
    EmailMessageThemeIdentifier identifier = EmailMessageThemeIdentifier.of("category", "key456");
    assertEquals("key456", identifier.key(), "key with alphanumeric should be accepted");
  }

  @Test
  void testParse() {
    EmailMessageThemeIdentifier identifier = EmailMessageThemeIdentifier.parse("category:key");
    EmailMessageThemeIdentifier expected = EmailMessageThemeIdentifier.of("category", "key");
    assertEquals(expected, identifier, "parsed identifier should match expected");
  }

  @Test
  void testParseWithBlankCategory() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageThemeIdentifier.parse(" :key"),
        "should throw IllegalArgumentException when parsed category is blank");
  }

  @Test
  void testParseWithBlankKey() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageThemeIdentifier.parse("category: "),
        "should throw IllegalArgumentException when parsed key is blank");
  }

  @Test
  void testParserInvalidString() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageThemeIdentifier.parse("invalid"),
        "should throw IllegalArgumentException when string has no colon separator");
  }

  @Test
  void testParserNull() {
    assertThrows(
        NullPointerException.class,
        () -> EmailMessageThemeIdentifier.parse(null),
        "should throw NullPointerException when input is null");
  }

  @Test
  void testParseWithInvalidCharactersInCategory() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageThemeIdentifier.parse("cat@gory:key"),
        "should throw IllegalArgumentException when parsed category contains invalid characters");
  }

  @Test
  void testParseWithInvalidCharactersInKey() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageThemeIdentifier.parse("category:k#y"),
        "should throw IllegalArgumentException when parsed key contains invalid characters");
  }

  @Test
  void testParseWithColonInKey() {
    assertThrows(
        IllegalArgumentException.class,
        () -> EmailMessageThemeIdentifier.parse("category:key:extra"),
        "should throw IllegalArgumentException when key contains colon");
  }

  @Test
  void testEqualsContract() {
    EqualsVerifier.forClass(EmailMessageThemeIdentifier.class)
        .suppress(nl.jqno.equalsverifier.Warning.NULL_FIELDS)
        .verify();
  }

  @Test
  void testToString() {
    EmailMessageThemeIdentifier identifier = EmailMessageThemeIdentifier.of("category", "key");
    assertNotNull(identifier.toString(), "toString should not return null");
  }
}

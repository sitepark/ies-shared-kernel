package com.sitepark.ies.sharedkernel.email;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jparams.verifier.tostring.ToStringVerifier;
import java.util.Map;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class TemplateEmailMessageTest {

  private static final EmailMessageTypeIdentifier MESSAGE_TYPE =
      EmailMessageTypeIdentifier.of("registration", "welcome");

  private static final EmailMessageThemeIdentifier THEME =
      EmailMessageThemeIdentifier.of("registration", "dark");

  @Test
  void testEquals() {
    EqualsVerifier.forClass(TemplateEmailMessage.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(TemplateEmailMessage.class).verify();
  }

  @Test
  void testMessageType() {
    TemplateEmailMessage message = TemplateEmailMessage.builder().messageType(MESSAGE_TYPE).build();
    assertEquals(MESSAGE_TYPE, message.messageType(), "unexpected messageType");
  }

  @Test
  void testMessageTypeNotNull() {
    assertThrows(
        NullPointerException.class,
        () -> TemplateEmailMessage.builder().messageType(null),
        "messageType should not be null");
  }

  @Test
  void testTheme() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder().theme(THEME).messageType(MESSAGE_TYPE).build();
    assertEquals(THEME, message.theme(), "unexpected theme");
  }

  @Test
  void testThemeNotNull() {
    assertThrows(
        NullPointerException.class,
        () -> TemplateEmailMessage.builder().theme(null),
        "theme should not be null");
  }

  @Test
  void testData() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder()
            .messageType(MESSAGE_TYPE)
            .data(builder -> builder.put("key", "value"))
            .build();
    assertEquals(Map.of("key", "value"), message.data(), "unexpected data");
  }

  @Test
  void testDataReturnsDefensiveCopy() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder()
            .messageType(MESSAGE_TYPE)
            .data(builder -> builder.put("key", "value"))
            .build();

    assertThrows(
        UnsupportedOperationException.class,
        () -> message.data().put("newKey", "newValue"),
        "data should return an immutable copy");
  }

  @Test
  void testLang() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder().messageType(MESSAGE_TYPE).lang("de").build();
    assertEquals("de", message.lang(), "unexpected lang");
  }

  @Test
  void testSetNullMessageType() {
    assertThrows(
        NullPointerException.class,
        () -> TemplateEmailMessage.builder().messageType(null),
        "messageType should not be null");
  }

  @Test
  void testBuildWithoutMessageType() {
    assertThrows(
        NullPointerException.class,
        () -> TemplateEmailMessage.builder().build(),
        "messageType is required for build");
  }

  @Test
  void testDataClearsExistingData() {
    TemplateEmailMessage.Builder builder =
        TemplateEmailMessage.builder().messageType(MESSAGE_TYPE).data(b -> b.put("key1", "value1"));
    builder.data(b -> b.put("key2", "value2"));

    assertEquals(
        Map.of("key2", "value2"), builder.build().data(), "data should replace existing data");
  }

  @Test
  void testToBuilder() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder()
            .messageType(MESSAGE_TYPE)
            .theme(THEME)
            .data(builder -> builder.put("key", "value"))
            .lang("de")
            .build();

    EmailMessageTypeIdentifier goodByeMessageType =
        EmailMessageTypeIdentifier.of("registration", "goodbye");
    assertEquals(
        TemplateEmailMessage.builder()
            .messageType(goodByeMessageType)
            .theme(THEME)
            .data(builder -> builder.put("key", "value"))
            .lang("de")
            .build(),
        message.toBuilder().messageType(goodByeMessageType).build(),
        "toBuilder should return a copy of the object");
  }

  @Test
  void testBuilderChaining() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder()
            .messageType(MESSAGE_TYPE)
            .theme(THEME)
            .data(builder -> builder.put("key", "value"))
            .lang("de")
            .build();

    assertNotNull(message, "builder chaining should produce a valid object");
  }

  @Test
  void testMinimalMessage() {
    assertEquals(
        MESSAGE_TYPE,
        TemplateEmailMessage.builder().messageType(MESSAGE_TYPE).build().messageType(),
        "minimal message should have messageType");
  }

  @Test
  void testSetNullTheme() {
    assertThrows(
        NullPointerException.class,
        () -> TemplateEmailMessage.builder().theme(null),
        "theme should not be null");
  }

  @Test
  void testThemeDefaultValue() {
    TemplateEmailMessage message = TemplateEmailMessage.builder().messageType(MESSAGE_TYPE).build();
    assertEquals(null, message.theme(), "theme should be null when not set");
  }

  @Test
  void testLangDefaultValue() {
    TemplateEmailMessage message = TemplateEmailMessage.builder().messageType(MESSAGE_TYPE).build();
    assertEquals(null, message.lang(), "lang should be null when not set");
  }

  @Test
  void testLangWithNull() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder().messageType(MESSAGE_TYPE).lang(null).build();
    assertEquals(null, message.lang(), "lang should accept null value");
  }

  @Test
  void testLangWithEmptyString() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder().messageType(MESSAGE_TYPE).lang("").build();
    assertEquals("", message.lang(), "lang should accept empty string");
  }

  @Test
  void testLangWithBlankString() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder().messageType(MESSAGE_TYPE).lang(" ").build();
    assertEquals(" ", message.lang(), "lang should accept blank string");
  }

  @Test
  void testDataDefaultValue() {
    TemplateEmailMessage message = TemplateEmailMessage.builder().messageType(MESSAGE_TYPE).build();
    assertEquals(Map.of(), message.data(), "data should be empty when not set");
  }

  @Test
  void testDataWithMultipleEntries() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder()
            .messageType(MESSAGE_TYPE)
            .data(
                builder ->
                    builder.put("key1", "value1").put("key2", "value2").put("key3", "value3"))
            .build();
    assertEquals(
        Map.of("key1", "value1", "key2", "value2", "key3", "value3"),
        message.data(),
        "data should contain all entries");
  }

  @Test
  void testDataWithComplexValue() {
    Map<String, String> complexValue = Map.of("nested", "value");
    TemplateEmailMessage message =
        TemplateEmailMessage.builder()
            .messageType(MESSAGE_TYPE)
            .data(builder -> builder.put("key", complexValue))
            .build();
    assertEquals(
        Map.of("key", complexValue), message.data(), "data should accept complex object values");
  }

  @Test
  void testMultipleLangCalls() {
    TemplateEmailMessage.Builder builder = TemplateEmailMessage.builder().messageType(MESSAGE_TYPE);
    builder.lang("de");
    builder.lang("en");
    assertEquals("en", builder.build().lang(), "last lang call should win");
  }

  @Test
  void testMultipleThemeCalls() {
    EmailMessageThemeIdentifier theme1 = EmailMessageThemeIdentifier.of("theme1", "light");
    EmailMessageThemeIdentifier theme2 = EmailMessageThemeIdentifier.of("theme2", "dark");

    TemplateEmailMessage.Builder builder = TemplateEmailMessage.builder().messageType(MESSAGE_TYPE);
    builder.theme(theme1);
    builder.theme(theme2);
    assertEquals(theme2, builder.build().theme(), "last theme call should win");
  }

  @Test
  void testMultipleMessageTypeCalls() {
    EmailMessageTypeIdentifier type1 = EmailMessageTypeIdentifier.of("type1", "key1");
    EmailMessageTypeIdentifier type2 = EmailMessageTypeIdentifier.of("type2", "key2");

    TemplateEmailMessage.Builder builder = TemplateEmailMessage.builder();
    builder.messageType(type1);
    builder.messageType(type2);
    assertEquals(type2, builder.build().messageType(), "last messageType call should win");
  }

  @Test
  void testToBuilderPreservesAllFields() {
    TemplateEmailMessage original =
        TemplateEmailMessage.builder()
            .messageType(MESSAGE_TYPE)
            .theme(THEME)
            .data(builder -> builder.put("key", "value"))
            .lang("de")
            .build();

    TemplateEmailMessage copy = original.toBuilder().build();
    assertEquals(original, copy, "toBuilder should preserve all fields");
  }

  @Test
  void testToBuilderWithMinimalMessage() {
    TemplateEmailMessage original =
        TemplateEmailMessage.builder().messageType(MESSAGE_TYPE).build();

    TemplateEmailMessage copy = original.toBuilder().build();
    assertEquals(original, copy, "toBuilder should work with minimal message");
  }

  @Test
  void testToBuilderModifyTheme() {
    TemplateEmailMessage original =
        TemplateEmailMessage.builder()
            .messageType(MESSAGE_TYPE)
            .theme(THEME)
            .data(builder -> builder.put("key", "value"))
            .lang("de")
            .build();

    EmailMessageThemeIdentifier newTheme = EmailMessageThemeIdentifier.of("new", "light");
    TemplateEmailMessage modified = original.toBuilder().theme(newTheme).build();

    assertEquals(newTheme, modified.theme(), "toBuilder should allow modifying theme");
  }

  @Test
  void testToBuilderModifyLang() {
    TemplateEmailMessage original =
        TemplateEmailMessage.builder()
            .messageType(MESSAGE_TYPE)
            .theme(THEME)
            .data(builder -> builder.put("key", "value"))
            .lang("de")
            .build();

    TemplateEmailMessage modified = original.toBuilder().lang("en").build();

    assertEquals("en", modified.lang(), "toBuilder should allow modifying lang");
  }

  @Test
  void testToBuilderModifyData() {
    TemplateEmailMessage original =
        TemplateEmailMessage.builder()
            .messageType(MESSAGE_TYPE)
            .theme(THEME)
            .data(builder -> builder.put("key", "value"))
            .lang("de")
            .build();

    TemplateEmailMessage modified =
        original.toBuilder().data(builder -> builder.put("newKey", "newValue")).build();

    assertEquals(
        Map.of("newKey", "newValue"), modified.data(), "toBuilder should allow modifying data");
  }

  @Test
  void testToBuilderIndependence() {
    TemplateEmailMessage original =
        TemplateEmailMessage.builder()
            .messageType(MESSAGE_TYPE)
            .theme(THEME)
            .data(builder -> builder.put("key", "value"))
            .lang("de")
            .build();

    TemplateEmailMessage.Builder builder = original.toBuilder();
    builder.lang("en");

    assertEquals("de", original.lang(), "modifying toBuilder should not affect original message");
  }

  @Test
  void testDataWithEmptyConfigurer() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder().messageType(MESSAGE_TYPE).data(builder -> {}).build();
    assertEquals(Map.of(), message.data(), "data should be empty with empty configurer");
  }

  @Test
  void testBuilderReuse() {
    TemplateEmailMessage.Builder builder = TemplateEmailMessage.builder().messageType(MESSAGE_TYPE);

    TemplateEmailMessage message1 = builder.build();
    TemplateEmailMessage message2 = builder.build();

    assertEquals(message1, message2, "reusing builder should produce equal objects");
  }

  @Test
  void testDataOverwritesSameKey() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder()
            .messageType(MESSAGE_TYPE)
            .data(builder -> builder.put("key", "value1").put("key", "value2"))
            .build();
    assertEquals(Map.of("key", "value2"), message.data(), "last value for key should win");
  }
}

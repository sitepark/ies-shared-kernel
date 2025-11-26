package com.sitepark.ies.sharedkernel.email;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jparams.verifier.tostring.ToStringVerifier;
import java.util.Map;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class TemplateEmailMessageTest {

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
    TemplateEmailMessage message = TemplateEmailMessage.builder().messageType("welcome").build();
    assertEquals("welcome", message.messageType(), "unexpected messageType");
  }

  @Test
  void testTheme() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder().messageType("welcome").theme("dark").build();
    assertEquals("dark", message.theme(), "unexpected theme");
  }

  @Test
  void testData() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder()
            .messageType("welcome")
            .data(builder -> builder.put("key", "value"))
            .build();
    assertEquals(Map.of("key", "value"), message.data(), "unexpected data");
  }

  @Test
  void testDataReturnsDefensiveCopy() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder()
            .messageType("welcome")
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
        TemplateEmailMessage.builder().messageType("welcome").lang("de").build();
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
  void testSetBlankMessageType() {
    assertThrows(
        IllegalArgumentException.class,
        () -> TemplateEmailMessage.builder().messageType(""),
        "messageType should not be blank");
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
        TemplateEmailMessage.builder().messageType("welcome").data(b -> b.put("key1", "value1"));
    builder.data(b -> b.put("key2", "value2"));

    assertEquals(
        Map.of("key2", "value2"), builder.build().data(), "data should replace existing data");
  }

  @Test
  void testToBuilder() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder()
            .messageType("welcome")
            .theme("dark")
            .data(builder -> builder.put("key", "value"))
            .lang("de")
            .build();

    assertEquals(
        TemplateEmailMessage.builder()
            .messageType("goodbye")
            .theme("dark")
            .data(builder -> builder.put("key", "value"))
            .lang("de")
            .build(),
        message.toBuilder().messageType("goodbye").build(),
        "toBuilder should return a copy of the object");
  }

  @Test
  void testBuilderChaining() {
    TemplateEmailMessage message =
        TemplateEmailMessage.builder()
            .messageType("welcome")
            .theme("dark")
            .data(builder -> builder.put("key", "value"))
            .lang("de")
            .build();

    assertNotNull(message, "builder chaining should produce a valid object");
  }

  @Test
  void testMinimalMessage() {
    assertEquals(
        "welcome",
        TemplateEmailMessage.builder().messageType("welcome").build().messageType(),
        "minimal message should have messageType");
  }
}

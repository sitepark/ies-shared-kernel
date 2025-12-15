package com.sitepark.ies.sharedkernel.email;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jparams.verifier.tostring.ToStringVerifier;
import java.util.List;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class EmailTest {

  private static final EmailMessageTypeIdentifier MESSAGE_TYPE =
      EmailMessageTypeIdentifier.of("registration", "welcome");

  private static EmailAddress createTestAddress(String address) {
    return EmailAddress.builder().address(address).build();
  }

  private static EmailMessage createTestMessage() {
    return TemplateEmailMessage.builder().messageType(MESSAGE_TYPE).build();
  }

  @Test
  void testEquals() {
    EqualsVerifier.forClass(Email.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(Email.class).verify();
  }

  @Test
  void testFrom() {
    EmailAddress from = createTestAddress("from@test.com");
    Email email =
        Email.builder()
            .from(from)
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .message(createTestMessage())
            .build();
    assertEquals(from, email.from(), "unexpected from address");
  }

  @Test
  void testReplyTo() {
    EmailAddress replyTo = createTestAddress("reply@test.com");

    assertEquals(
        List.of(replyTo),
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .replyTo(builder -> builder.add(replyTo))
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .message(createTestMessage())
            .build()
            .replyTo(),
        "unexpected replyTo address");
  }

  @Test
  void testReplyToDefaultsToFrom() {
    EmailAddress from = createTestAddress("from@test.com");

    assertEquals(
        List.of(from),
        Email.builder()
            .from(from)
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .message(createTestMessage())
            .build()
            .replyTo(),
        "replyTo should default to from address");
  }

  @Test
  void testReplyToReturnsDefensiveCopy() {
    Email email =
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .message(createTestMessage())
            .build();

    assertThrows(
        UnsupportedOperationException.class,
        () -> email.replyTo().add(createTestAddress("new@test.com")),
        "replyTo should return an immutable copy");
  }

  @Test
  void testTo() {
    EmailAddress to = createTestAddress("to@test.com");

    assertEquals(
        List.of(to),
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(builder -> builder.add(to))
            .message(createTestMessage())
            .build()
            .to(),
        "unexpected to addresses");
  }

  @Test
  void testToReturnsDefensiveCopy() {
    Email email =
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .message(createTestMessage())
            .build();

    assertThrows(
        UnsupportedOperationException.class,
        () -> email.to().add(createTestAddress("new@test.com")),
        "to should return an immutable copy");
  }

  @Test
  void testCc() {
    EmailAddress cc = createTestAddress("cc@test.com");

    assertEquals(
        List.of(cc),
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .cc(builder -> builder.add(cc))
            .message(createTestMessage())
            .build()
            .cc(),
        "unexpected cc addresses");
  }

  @Test
  void testCcReturnsDefensiveCopy() {
    Email email =
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .message(createTestMessage())
            .build();

    assertThrows(
        UnsupportedOperationException.class,
        () -> email.cc().add(createTestAddress("new@test.com")),
        "cc should return an immutable copy");
  }

  @Test
  void testBcc() {
    EmailAddress bcc = createTestAddress("bcc@test.com");

    assertEquals(
        List.of(bcc),
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .bcc(builder -> builder.add(bcc))
            .message(createTestMessage())
            .build()
            .bcc(),
        "unexpected bcc addresses");
  }

  @Test
  void testBccReturnsDefensiveCopy() {
    Email email =
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .message(createTestMessage())
            .build();

    assertThrows(
        UnsupportedOperationException.class,
        () -> email.bcc().add(createTestAddress("new@test.com")),
        "bcc should return an immutable copy");
  }

  @Test
  void testMessage() {
    EmailMessage message = createTestMessage();

    assertEquals(
        message,
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .message(message)
            .build()
            .message(),
        "unexpected message");
  }

  @Test
  void testSetNullFrom() {
    assertThrows(
        NullPointerException.class, () -> Email.builder().from(null), "from should not be null");
  }

  @Test
  void testSetNullMessage() {
    assertThrows(
        NullPointerException.class,
        () -> Email.builder().message(null),
        "message should not be null");
  }

  @Test
  void testBuildWithoutFrom() {
    assertThrows(
        NullPointerException.class,
        () ->
            Email.builder()
                .to(builder -> builder.add(createTestAddress("to@test.com")))
                .message(createTestMessage())
                .build(),
        "from is required for build");
  }

  @Test
  void testBuildWithoutTo() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            Email.builder()
                .from(createTestAddress("from@test.com"))
                .message(createTestMessage())
                .build(),
        "to is required for build");
  }

  @Test
  void testBuildWithEmptyTo() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            Email.builder()
                .from(createTestAddress("from@test.com"))
                .to(builder -> {})
                .message(createTestMessage())
                .build(),
        "to must not be empty");
  }

  @Test
  void testBuildWithoutMessage() {
    assertThrows(
        NullPointerException.class,
        () ->
            Email.builder()
                .from(createTestAddress("from@test.com"))
                .to(builder -> builder.add(createTestAddress("to@test.com")))
                .build(),
        "message is required for build");
  }

  @Test
  void testReplyToClearsExistingAddresses() {
    Email.Builder builder =
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(builder2 -> builder2.add(createTestAddress("to@test.com")))
            .replyTo(b -> b.add(createTestAddress("reply1@test.com")))
            .message(createTestMessage());
    builder.replyTo(b -> b.add(createTestAddress("reply2@test.com")));

    assertEquals(
        List.of(createTestAddress("reply2@test.com")),
        builder.build().replyTo(),
        "replyTo should replace existing addresses");
  }

  @Test
  void testToClearsExistingAddresses() {
    Email.Builder builder =
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(b -> b.add(createTestAddress("to1@test.com")))
            .message(createTestMessage());
    builder.to(b -> b.add(createTestAddress("to2@test.com")));

    assertEquals(
        List.of(createTestAddress("to2@test.com")),
        builder.build().to(),
        "to should replace existing addresses");
  }

  @Test
  void testCcClearsExistingAddresses() {
    Email.Builder builder =
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(b -> b.add(createTestAddress("to@test.com")))
            .cc(b -> b.add(createTestAddress("cc1@test.com")))
            .message(createTestMessage());
    builder.cc(b -> b.add(createTestAddress("cc2@test.com")));

    assertEquals(
        List.of(createTestAddress("cc2@test.com")),
        builder.build().cc(),
        "cc should replace existing addresses");
  }

  @Test
  void testBccClearsExistingAddresses() {
    Email.Builder builder =
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(b -> b.add(createTestAddress("to@test.com")))
            .bcc(b -> b.add(createTestAddress("bcc1@test.com")))
            .message(createTestMessage());
    builder.bcc(b -> b.add(createTestAddress("bcc2@test.com")));

    assertEquals(
        List.of(createTestAddress("bcc2@test.com")),
        builder.build().bcc(),
        "bcc should replace existing addresses");
  }

  @Test
  void testToBuilder() {
    Email email =
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .replyTo(builder -> builder.add(createTestAddress("reply@test.com")))
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .cc(builder -> builder.add(createTestAddress("cc@test.com")))
            .bcc(builder -> builder.add(createTestAddress("bcc@test.com")))
            .message(createTestMessage())
            .build();

    assertEquals(
        Email.builder()
            .from(createTestAddress("newfrom@test.com"))
            .replyTo(builder -> builder.add(createTestAddress("reply@test.com")))
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .cc(builder -> builder.add(createTestAddress("cc@test.com")))
            .bcc(builder -> builder.add(createTestAddress("bcc@test.com")))
            .message(createTestMessage())
            .build(),
        email.toBuilder().from(createTestAddress("newfrom@test.com")).build(),
        "toBuilder should return a copy of the object");
  }

  @Test
  void testBuilderChaining() {
    Email email =
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .message(createTestMessage())
            .build();

    assertNotNull(email, "builder chaining should produce a valid object");
  }

  @Test
  void testMinimalEmailFrom() {
    assertEquals(
        createTestAddress("from@test.com"),
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .message(createTestMessage())
            .build()
            .from(),
        "minimal email should have from");
  }

  @Test
  void testMinimalEmailTo() {
    assertEquals(
        List.of(createTestAddress("to@test.com")),
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .message(createTestMessage())
            .build()
            .to(),
        "minimal email should have to");
  }

  @Test
  void testMinimalEmailHasMessage() {
    assertNotNull(
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .message(createTestMessage())
            .build()
            .message(),
        "minimal email should have message");
  }

  @Test
  void testMinimalEmailCcIsEmpty() {
    assertTrue(
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .message(createTestMessage())
            .build()
            .cc()
            .isEmpty(),
        "minimal email should have empty cc");
  }

  @Test
  void testMinimalEmailBccIsEmpty() {
    assertTrue(
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(builder -> builder.add(createTestAddress("to@test.com")))
            .message(createTestMessage())
            .build()
            .bcc()
            .isEmpty(),
        "minimal email should have empty bcc");
  }

  @Test
  void testMultipleToAddresses() {
    assertEquals(
        List.of(createTestAddress("to1@test.com"), createTestAddress("to2@test.com")),
        Email.builder()
            .from(createTestAddress("from@test.com"))
            .to(
                builder -> {
                  builder.add(createTestAddress("to1@test.com"));
                  builder.add(createTestAddress("to2@test.com"));
                })
            .message(createTestMessage())
            .build()
            .to(),
        "email should support multiple to addresses");
  }
}

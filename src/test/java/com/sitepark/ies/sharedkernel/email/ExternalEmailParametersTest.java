package com.sitepark.ies.sharedkernel.email;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ExternalEmailParametersTest {

  @Test
  void testModifiableReplyToOriginList() {
    List<EmailAddress> replyTo = new ArrayList<>();
    replyTo.add(EmailAddress.builder().address("test@test.com").build());
    ExternalEmailParameters externalEmailParameters =
        new ExternalEmailParameters(null, replyTo, null, null);
    replyTo.add(EmailAddress.builder().address("test2@test.com").build());

    assertEquals(
        List.of(EmailAddress.builder().address("test@test.com").build()),
        externalEmailParameters.replyTo(),
        "replyTo should be unmodifiable");
  }

  @Test
  void testUnmodifiableReplyTo() {
    List<EmailAddress> replyTo = new ArrayList<>();
    replyTo.add(EmailAddress.builder().address("test@test.com").build());
    ExternalEmailParameters externalEmailParameters =
        new ExternalEmailParameters(null, replyTo, null, null);
    assertThrows(
        UnsupportedOperationException.class,
        () -> externalEmailParameters.replyTo().clear(),
        "replyTo should be unmodifiable");
  }
}

package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class SignedDataTest {

  private static final Instant ISSUED_AT = Instant.parse("2026-01-01T00:00:00Z");
  private static final Instant EXPIRES_AT = Instant.parse("2026-01-08T00:00:00Z");

  @Test
  void testRenewAtIsHalfwayThroughTheLifetime() {
    SignedData signedData = new SignedData("token", ISSUED_AT, EXPIRES_AT);
    assertEquals(
        Instant.parse("2026-01-04T12:00:00Z"),
        signedData.renewAt(),
        "renewAt should lie halfway between issuedAt and expiresAt");
  }

  @Test
  void testExpiresAtMustBeAfterIssuedAt() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new SignedData("token", ISSUED_AT, ISSUED_AT),
        "a token without lifetime should be rejected");
  }

  @Test
  void testToStringHidesToken() {
    SignedData signedData = new SignedData("secret-token", ISSUED_AT, EXPIRES_AT);
    assertFalse(
        signedData.toString().contains("secret-token"), "toString should not reveal the token");
  }
}

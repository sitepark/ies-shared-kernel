package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.mockito.Mockito.mock;

import java.time.Instant;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class CodeVerificationChallengeTest {

  private static final String CHALLENGE_ID = "test-challenge-id";
  private static final int CODE = 123456;
  private static final CodeVerificationPayload PAYLOAD = mock(CodeVerificationPayload.class);
  private static final Instant CREATED_AT = Instant.parse("2025-01-01T10:00:00Z");
  private static final Instant EXPIRES_AT = Instant.parse("2025-01-01T10:15:00Z");
  private static final int ATTEMPTS = 0;

  @Test
  void testRecordCreation() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    assertNotNull(challenge, "record should be created successfully");
  }

  @Test
  void testChallengeId() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    assertEquals(CHALLENGE_ID, challenge.challengeId(), "challengeId should match input");
  }

  @Test
  void testCode() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    assertEquals(CODE, challenge.code(), "code should match input");
  }

  @Test
  void testPayload() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    assertEquals(PAYLOAD, challenge.payload(), "payload should match input");
  }

  @Test
  void testCreatedAt() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    assertEquals(CREATED_AT, challenge.createdAt(), "createdAt should match input");
  }

  @Test
  void testExpiresAt() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    assertEquals(EXPIRES_AT, challenge.expiresAt(), "expiresAt should match input");
  }

  @Test
  void testAttempts() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    assertEquals(ATTEMPTS, challenge.attempts(), "attempts should match input");
  }

  @Test
  void testIncrementAttemptsIncreasesByOne() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    CodeVerificationChallenge incremented = challenge.incrementAttempts();
    assertEquals(
        ATTEMPTS + 1, incremented.attempts(), "incrementAttempts should increase attempts by one");
  }

  @Test
  void testIncrementAttemptsCreatesNewInstance() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    CodeVerificationChallenge incremented = challenge.incrementAttempts();
    assertNotSame(
        challenge, incremented, "incrementAttempts should create new instance for immutability");
  }

  @Test
  void testIncrementAttemptsPreservesChallengeId() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    CodeVerificationChallenge incremented = challenge.incrementAttempts();
    assertEquals(
        challenge.challengeId(),
        incremented.challengeId(),
        "incrementAttempts should preserve challengeId");
  }

  @Test
  void testIncrementAttemptsPreservesCode() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    CodeVerificationChallenge incremented = challenge.incrementAttempts();
    assertEquals(challenge.code(), incremented.code(), "incrementAttempts should preserve code");
  }

  @Test
  void testIncrementAttemptsPreservesPayload() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    CodeVerificationChallenge incremented = challenge.incrementAttempts();
    assertEquals(
        challenge.payload(), incremented.payload(), "incrementAttempts should preserve payload");
  }

  @Test
  void testIncrementAttemptsPreservesCreatedAt() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    CodeVerificationChallenge incremented = challenge.incrementAttempts();
    assertEquals(
        challenge.createdAt(),
        incremented.createdAt(),
        "incrementAttempts should preserve createdAt");
  }

  @Test
  void testIncrementAttemptsPreservesExpiresAt() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    CodeVerificationChallenge incremented = challenge.incrementAttempts();
    assertEquals(
        challenge.expiresAt(),
        incremented.expiresAt(),
        "incrementAttempts should preserve expiresAt");
  }

  @Test
  void testIncrementAttemptsMultipleTimes() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    CodeVerificationChallenge incremented = challenge.incrementAttempts().incrementAttempts();
    assertEquals(
        ATTEMPTS + 2, incremented.attempts(), "multiple increments should accumulate correctly");
  }

  @Test
  void testIncrementAttemptsFromNonZero() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, 5);
    CodeVerificationChallenge incremented = challenge.incrementAttempts();
    assertEquals(6, incremented.attempts(), "incrementAttempts should work from non-zero value");
  }

  @Test
  void testWithNullChallengeId() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(null, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    assertEquals(null, challenge.challengeId(), "null challengeId should be accepted");
  }

  @Test
  void testWithNullPayload() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(CHALLENGE_ID, CODE, null, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    assertEquals(null, challenge.payload(), "null payload should be accepted");
  }

  @Test
  void testWithNullCreatedAt() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(CHALLENGE_ID, CODE, PAYLOAD, null, EXPIRES_AT, ATTEMPTS);
    assertEquals(null, challenge.createdAt(), "null createdAt should be accepted");
  }

  @Test
  void testWithNullExpiresAt() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, null, ATTEMPTS);
    assertEquals(null, challenge.expiresAt(), "null expiresAt should be accepted");
  }

  @Test
  void testWithZeroCode() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(CHALLENGE_ID, 0, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    assertEquals(0, challenge.code(), "zero code should be accepted");
  }

  @Test
  void testWithNegativeCode() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(CHALLENGE_ID, -1, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    assertEquals(-1, challenge.code(), "negative code should be accepted");
  }

  @Test
  void testWithZeroAttempts() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, 0);
    assertEquals(0, challenge.attempts(), "zero attempts should be accepted");
  }

  @Test
  void testWithNegativeAttempts() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, -1);
    assertEquals(-1, challenge.attempts(), "negative attempts should be accepted");
  }

  @Test
  void testWithLargeCode() {
    int largeCode = 999999;
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, largeCode, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    assertEquals(largeCode, challenge.code(), "large code value should be accepted");
  }

  @Test
  void testWithLargeAttempts() {
    int largeAttempts = 1000;
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, largeAttempts);
    assertEquals(largeAttempts, challenge.attempts(), "large attempts value should be accepted");
  }

  @Test
  void testWithCreatedAtAfterExpiresAt() {
    Instant laterCreatedAt = Instant.parse("2025-01-01T10:20:00Z");
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, laterCreatedAt, EXPIRES_AT, ATTEMPTS);
    assertEquals(
        laterCreatedAt,
        challenge.createdAt(),
        "createdAt after expiresAt should be accepted without validation");
  }

  @Test
  void testWithSameCreatedAtAndExpiresAt() {
    Instant sameInstant = Instant.parse("2025-01-01T10:00:00Z");
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, sameInstant, sameInstant, ATTEMPTS);
    assertEquals(
        challenge.createdAt(),
        challenge.expiresAt(),
        "same createdAt and expiresAt should be accepted");
  }

  @Test
  void testEqualsContract() {
    EqualsVerifier.forClass(CodeVerificationChallenge.class)
        .suppress(nl.jqno.equalsverifier.Warning.NULL_FIELDS)
        .verify();
  }

  @Test
  void testToString() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    assertNotNull(challenge.toString(), "toString should not return null");
  }

  @Test
  void testEqualsSameValues() {
    CodeVerificationChallenge challenge1 =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    CodeVerificationChallenge challenge2 =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    assertEquals(challenge1, challenge2, "challenges with same values should be equal");
  }

  @Test
  void testNotEqualsDifferentAttempts() {
    CodeVerificationChallenge challenge1 =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    CodeVerificationChallenge challenge2 =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS + 1);
    assertNotEquals(
        challenge1, challenge2, "challenges with different attempts should not be equal");
  }

  @Test
  void testIncrementAttemptsChangesEquality() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge(
            CHALLENGE_ID, CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    CodeVerificationChallenge incremented = challenge.incrementAttempts();
    assertNotEquals(
        challenge,
        incremented,
        "incremented challenge should not be equal to original due to different attempts");
  }

  @Test
  void testWithEmptyChallengeId() {
    CodeVerificationChallenge challenge =
        new CodeVerificationChallenge("", CODE, PAYLOAD, CREATED_AT, EXPIRES_AT, ATTEMPTS);
    assertEquals("", challenge.challengeId(), "empty challengeId should be accepted");
  }
}

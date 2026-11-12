package com.sitepark.ies.sharedkernel.security;

import java.time.Instant;

/**
 * A code verification challenge.
 *
 * @param sendCode {@code true} if the code has to be sent to the user. A {@link
 *     CodeVerificationService} returns {@code false} if the code of a still running challenge was
 *     already sent, so that repeated starts do not send further emails.
 */
public record CodeVerificationChallenge(
    String challengeId,
    int code,
    CodeVerificationPayload payload,
    Instant createdAt,
    Instant expiresAt,
    int attempts,
    boolean sendCode) {

  /** Creates a challenge whose code has to be sent to the user. */
  public CodeVerificationChallenge(
      String challengeId,
      int code,
      CodeVerificationPayload payload,
      Instant createdAt,
      Instant expiresAt,
      int attempts) {
    this(challengeId, code, payload, createdAt, expiresAt, attempts, true);
  }

  public CodeVerificationChallenge incrementAttempts() {
    return new CodeVerificationChallenge(
        this.challengeId,
        this.code,
        this.payload,
        this.createdAt,
        this.expiresAt,
        this.attempts + 1,
        this.sendCode);
  }
}

package com.sitepark.ies.sharedkernel.security;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

/**
 * A token created by {@link SignedDataService#sign}.
 *
 * @param token the opaque token the client passes back unchanged
 * @param issuedAt when the token was created
 * @param expiresAt when the token stops being valid
 */
public record SignedData(String token, Instant issuedAt, Instant expiresAt) {

  public SignedData {
    Objects.requireNonNull(token, "token is null");
    Objects.requireNonNull(issuedAt, "issuedAt is null");
    Objects.requireNonNull(expiresAt, "expiresAt is null");
    if (!expiresAt.isAfter(issuedAt)) {
      throw new IllegalArgumentException("expiresAt must be after issuedAt");
    }
  }

  /**
   * The moment the token should be regenerated: halfway through its lifetime, so a regeneration
   * that fails once leaves time for the next attempt.
   */
  public Instant renewAt() {
    return this.issuedAt.plus(Duration.between(this.issuedAt, this.expiresAt).dividedBy(2));
  }

  @Override
  public String toString() {
    return "SignedData[issuedAt=" + this.issuedAt + ", expiresAt=" + this.expiresAt + "]";
  }
}

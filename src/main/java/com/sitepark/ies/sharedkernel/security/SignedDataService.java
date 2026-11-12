package com.sitepark.ies.sharedkernel.security;

import java.time.Duration;
import java.util.Map;

/**
 * Signs data that the IES writes out (for example into a published PHP file) and that a client
 * later sends back to the IES, so that the IES can trust it again.
 *
 * <p>The signed data travels as an opaque token. The client passes the token unchanged; the IES
 * takes the data only from the verified token, never from values the client sends alongside it.
 *
 * <p>Every token is bound to a {@code purpose} and to the tenant that signed it, and it always
 * expires. Whoever writes a token out is responsible for regenerating it before {@link
 * SignedData#renewAt()}.
 */
public interface SignedDataService {

  /** The lifetime of a token if none is given. */
  Duration DEFAULT_TTL = Duration.ofDays(7);

  /** The longest lifetime a token may have. */
  Duration MAX_TTL = Duration.ofDays(90);

  /**
   * Signs the data for the purpose with {@link #DEFAULT_TTL}.
   *
   * @param purpose what the token is meant for, e.g. {@code web-account}
   * @param data the data to sign; must be serializable as JSON
   * @return the token and its lifetime
   * @throws IllegalArgumentException if the purpose is invalid
   */
  default SignedData sign(String purpose, Map<String, Object> data) {
    return this.sign(purpose, data, DEFAULT_TTL);
  }

  /**
   * Signs the data for the purpose.
   *
   * @param purpose what the token is meant for, e.g. {@code web-account}
   * @param data the data to sign; must be serializable as JSON
   * @param ttl the lifetime of the token, positive and at most {@link #MAX_TTL}
   * @return the token and its lifetime
   * @throws IllegalArgumentException if the purpose or the ttl is invalid
   */
  SignedData sign(String purpose, Map<String, Object> data, Duration ttl);

  /**
   * Verifies the token and returns the data it carries.
   *
   * @param purpose the purpose the token must have been signed for
   * @param token the token as returned by {@link SignedData#token()}
   * @return the signed data
   * @throws ExpiredSignedDataException if the token is valid but expired
   * @throws InvalidSignedDataException if the token is not valid for this purpose and tenant
   */
  Map<String, Object> verify(String purpose, String token);
}

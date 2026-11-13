package com.sitepark.ies.sharedkernel.security;

import java.io.Serial;

/**
 * Thrown if signed data was valid but has expired, usually because the file that carries the token
 * was not regenerated in time.
 */
public class ExpiredSignedDataException extends InvalidSignedDataException {

  @Serial private static final long serialVersionUID = 1L;

  public ExpiredSignedDataException(String message) {
    super(message);
  }

  public ExpiredSignedDataException(String message, Throwable cause) {
    super(message, cause);
  }
}

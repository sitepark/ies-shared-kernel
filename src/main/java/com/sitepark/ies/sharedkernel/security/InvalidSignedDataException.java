package com.sitepark.ies.sharedkernel.security;

import com.sitepark.ies.sharedkernel.domain.DomainException;
import java.io.Serial;

/**
 * Thrown if signed data cannot be trusted. The message stays generic on purpose; the reason is only
 * logged.
 */
public class InvalidSignedDataException extends DomainException {

  @Serial private static final long serialVersionUID = 1L;

  public InvalidSignedDataException(String message) {
    super(message);
  }

  public InvalidSignedDataException(String message, Throwable cause) {
    super(message, cause);
  }
}

package com.sitepark.ies.sharedkernel.security;

import com.sitepark.ies.sharedkernel.domain.DomainException;
import java.io.Serial;

public class AccessDeniedException extends DomainException {

  @Serial private static final long serialVersionUID = 1L;

  public AccessDeniedException(String message) {
    super(message);
  }

  public AccessDeniedException(String message, Throwable cause) {
    super(message, cause);
  }
}

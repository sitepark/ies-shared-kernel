package com.sitepark.ies.sharedkernel.domain;

import java.io.Serial;

public class DomainException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1;

  public DomainException() {
    super();
  }

  public DomainException(String message) {
    super(message);
  }

  public DomainException(String message, Throwable cause) {
    super(message, cause);
  }

  public DomainException(Throwable cause) {
    super(cause);
  }

  protected DomainException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

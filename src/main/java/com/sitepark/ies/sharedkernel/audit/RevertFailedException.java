package com.sitepark.ies.sharedkernel.audit;

import java.io.Serial;

/** The <code>RevertFailedException</code> exception is thrown when a revert operation fails. */
public class RevertFailedException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  private final RevertRequest request;

  public RevertFailedException(RevertRequest request, String message) {
    super(message);
    this.request = request;
  }

  public RevertFailedException(RevertRequest request, String message, Throwable cause) {
    super(message, cause);
    this.request = request;
  }

  public RevertRequest getRequest() {
    return this.request;
  }
}

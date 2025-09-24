package com.sitepark.ies.sharedkernel.patch;

import java.io.Serial;

public class PatchException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  public PatchException(String message) {
    super(message);
  }

  public PatchException(String message, Throwable cause) {
    super(message, cause);
  }
}

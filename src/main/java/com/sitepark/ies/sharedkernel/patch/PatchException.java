package com.sitepark.ies.sharedkernel.patch;

import com.sitepark.ies.sharedkernel.domain.DomainException;
import java.io.Serial;

public class PatchException extends DomainException {

  @Serial private static final long serialVersionUID = 1L;

  public PatchException(String message) {
    super(message);
  }

  public PatchException(String message, Throwable cause) {
    super(message, cause);
  }
}

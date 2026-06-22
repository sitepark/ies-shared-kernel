package com.sitepark.ies.sharedkernel.security;

import com.sitepark.ies.sharedkernel.domain.DomainException;
import java.io.Serial;
import org.jspecify.annotations.Nullable;

public class PermissionParseException extends DomainException {

  @Serial private static final long serialVersionUID = 1L;

  private final String data;

  public PermissionParseException(String data, String message) {
    this(data, message, null);
  }

  public PermissionParseException(String data, String message, @Nullable Throwable cause) {
    super(message, cause);
    this.data = data;
  }

  public String getData() {
    return this.data;
  }
}

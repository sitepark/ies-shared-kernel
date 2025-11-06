package com.sitepark.ies.sharedkernel.security;

import com.sitepark.ies.sharedkernel.domain.DomainException;
import java.io.Serial;

public class PermissionDeserializeException extends DomainException {

  @Serial private static final long serialVersionUID = 1L;

  private final String data;

  public PermissionDeserializeException(String data, String message) {
    this(data, message, null);
  }

  public PermissionDeserializeException(String data, String message, Throwable cause) {
    super(message, cause);
    this.data = data;
  }

  public String getData() {
    return this.data;
  }
}

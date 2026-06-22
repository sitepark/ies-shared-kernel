package com.sitepark.ies.sharedkernel.security;

import com.sitepark.ies.sharedkernel.domain.DomainException;
import java.io.Serial;
import org.jspecify.annotations.Nullable;

public class PermissionSerializeException extends DomainException {

  @Serial private static final long serialVersionUID = 1L;

  private final Object object;

  public PermissionSerializeException(Object object, String message) {
    this(object, message, null);
  }

  public PermissionSerializeException(Object object, String message, @Nullable Throwable cause) {
    super(message, cause);
    this.object = object;
  }

  public Object getObject() {
    return this.object;
  }
}

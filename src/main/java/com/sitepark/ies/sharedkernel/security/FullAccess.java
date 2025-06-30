package com.sitepark.ies.sharedkernel.security;

import java.util.Objects;

public final class FullAccess implements Permission {
  public static final String TYPE = "FULL_ACCESS";

  @Override
  public String getType() {
    return TYPE;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getType());
  }

  @Override
  @SuppressWarnings("PMD.SimplifyBooleanReturns")
  public boolean equals(Object obj) {
    if (!(obj instanceof FullAccess that)) {
      return false;
    }
    return Objects.equals(this.getType(), that.getType());
  }
}

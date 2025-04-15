package com.sitepark.ies.sharedkernel.security.domain;

public class FullAccess implements Permission {
  public static final String TYPE = "FULL_ACCESS";

  @Override
  public String getType() {
    return TYPE;
  }
}

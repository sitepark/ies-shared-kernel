package com.sitepark.ies.sharedkernel.security;

import com.fasterxml.jackson.annotation.JsonCreator;

public final class InternalIdentity implements Identity {

  private static final InternalIdentity INSTANCE = new InternalIdentity();

  private InternalIdentity() {}

  @Override
  public String getType() {
    return "internal";
  }

  @JsonCreator
  static InternalIdentity getInstance() {
    return INSTANCE;
  }

  @Override
  public String toString() {
    return "InternalIdentity{}";
  }

  @Override
  public int hashCode() {
    return InternalIdentity.class.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof InternalIdentity;
  }
}

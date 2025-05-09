package com.sitepark.ies.sharedkernel.anchor.exception;

import com.sitepark.ies.sharedkernel.anchor.domain.Anchor;
import java.io.Serial;

/**
 * The <code>AnchorAlreadyExistsException</code> exception is thrown when attempting to create a new
 * anchor that already exists, violating the uniqueness constraint for anchors.
 */
public class AnchorAlreadyExistsException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  private final Anchor anchor;

  private final String owner;

  public AnchorAlreadyExistsException(Anchor anchor, String owner) {
    this.anchor = anchor;
    this.owner = owner;
  }

  public Anchor getAnchor() {
    return this.anchor;
  }

  public String getOwner() {
    return this.owner;
  }

  @Override
  public String getMessage() {
    return "Anchor " + this.anchor + " already exists for user " + this.owner;
  }
}

package com.sitepark.ies.sharedkernel.anchor;

import java.io.Serial;

/**
 * The <code>AnchorAlreadyExistsException</code> exception is thrown when attempting to create a new
 * anchor that already exists, violating the uniqueness constraint for anchors.
 */
public class AnchorAlreadyExistsException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  private final Anchor anchor;

  private final String id;

  public AnchorAlreadyExistsException(Anchor anchor, String id) {
    this.anchor = anchor;
    this.id = id;
  }

  public Anchor getAnchor() {
    return this.anchor;
  }

  public String getId() {
    return this.id;
  }

  @Override
  public String getMessage() {
    return "Anchor " + this.anchor + " already exists for id " + this.id;
  }
}

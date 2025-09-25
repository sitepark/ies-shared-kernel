package com.sitepark.ies.sharedkernel.anchor;

import com.sitepark.ies.sharedkernel.domain.DomainException;
import java.io.Serial;

/**
 * The <code>AnchorNotFoundException</code> exception is thrown when an anchor cannot be found or
 * does not exist in the system, typically when trying to access or manipulate an anchor that is not
 * present.
 */
public class AnchorNotFoundException extends DomainException {

  @Serial private static final long serialVersionUID = 1L;

  private final Anchor anchor;

  public AnchorNotFoundException(Anchor anchor) {
    this.anchor = anchor;
  }

  public Anchor getAnchor() {
    return this.anchor;
  }

  @Override
  public String getMessage() {
    return "Anchor " + this.anchor + " not found";
  }
}

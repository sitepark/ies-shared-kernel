package com.sitepark.ies.sharedkernel.audit;

import java.io.Serial;

/**
 * The <code>CreateAuditLogEntryFailedException</code> exception is thrown when the creation of an
 * audit log entry fails.
 */
public class CreateAuditLogEntryFailedException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  private final String entityType;

  private final String entityId;

  public CreateAuditLogEntryFailedException(String entityType, String entityId, Throwable t) {
    super(t);
    this.entityType = entityType;
    this.entityId = entityId;
  }

  public String getEntityType() {
    return this.entityType;
  }

  public String getEntityId() {
    return this.entityId;
  }

  @Override
  public String getMessage() {
    return "Create audit-log entry failed: " + this.entityType + "." + this.entityId;
  }
}

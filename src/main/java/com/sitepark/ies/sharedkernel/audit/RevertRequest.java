package com.sitepark.ies.sharedkernel.audit;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public record RevertRequest(
    String id,
    String entityType,
    String entityId,
    String entityName,
    String action,
    String backwardData,
    String forwardData,
    Instant changedAt,
    String parentId)
    implements Serializable {
  @Serial private static final long serialVersionUID = 1L;
}

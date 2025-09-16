package com.sitepark.ies.sharedkernel.audit;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public record RevertRequest(
    String entityType,
    String entityId,
    String action,
    String oldData,
    String newData,
    Instant changedAt,
    String batchId)
    implements Serializable {
  @Serial private static final long serialVersionUID = 1L;
}

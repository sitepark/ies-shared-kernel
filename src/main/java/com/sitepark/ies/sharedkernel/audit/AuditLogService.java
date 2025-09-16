package com.sitepark.ies.sharedkernel.audit;

import java.time.Instant;

public interface AuditLogService {

  String createAuditLog(CreateAuditLogRequest command);

  /**
   * Creates an audit batch with the specified creation time.
   *
   * @return the ID of the created audit batch
   */
  String createAuditBatch(Instant createdAt);
}

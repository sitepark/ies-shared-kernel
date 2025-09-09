package com.sitepark.ies.sharedkernel.audit;

import java.util.UUID;

public interface AuditLogService {

  default String generateAuditBatchId() {
    return UUID.randomUUID().toString();
  }

  String createAuditLog(CreateAuditLogCommand command);

  String createAuditBatch(CreateAuditBatchCommand command);
}

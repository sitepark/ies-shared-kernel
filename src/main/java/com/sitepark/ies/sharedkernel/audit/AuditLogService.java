package com.sitepark.ies.sharedkernel.audit;

public interface AuditLogService {
  String createAuditLog(CreateAuditLogCommand command);

  String createAuditBatch(CreateAuditBatchCommand command);
}

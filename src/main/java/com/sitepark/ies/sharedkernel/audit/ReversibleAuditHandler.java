package com.sitepark.ies.sharedkernel.audit;

public interface ReversibleAuditHandler {
  boolean supports(String entityType, String action);

  void revert(AuditLogEntity autoLog);
}

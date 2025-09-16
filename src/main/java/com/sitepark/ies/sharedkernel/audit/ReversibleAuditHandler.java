package com.sitepark.ies.sharedkernel.audit;

public interface ReversibleAuditHandler {
  String getEntityType();

  void revert(RevertRequest revertRequest);
}

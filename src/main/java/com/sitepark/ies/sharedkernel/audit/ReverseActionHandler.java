package com.sitepark.ies.sharedkernel.audit;

public interface ReverseActionHandler {
  String getEntityType();

  void revert(RevertRequest revertRequest);
}

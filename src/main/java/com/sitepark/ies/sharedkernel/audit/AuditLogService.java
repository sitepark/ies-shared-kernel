package com.sitepark.ies.sharedkernel.audit;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AuditLogService {
  String createAuditLog(CreateAuditLogRequest request);

  List<String> getRecursiveChildIds(String parentId);

  <T> Optional<T> getOldData(String auditLogId, Class<T> valueType) throws IOException;

  <T> T deserialize(String oldData, Class<T> valueType) throws IOException;

  String serialize(Object value) throws IOException;
}

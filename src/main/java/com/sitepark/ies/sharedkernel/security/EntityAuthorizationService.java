package com.sitepark.ies.sharedkernel.security;

import java.util.List;

public interface EntityAuthorizationService {

  Class<?> type();

  boolean isCreatable();

  boolean isReadable(List<String> ids);

  boolean isReadable(String id);

  boolean isWritable(List<String> id);

  boolean isWritable(String id);

  boolean isRemovable(List<String> id);

  boolean isRemovable(String id);
}

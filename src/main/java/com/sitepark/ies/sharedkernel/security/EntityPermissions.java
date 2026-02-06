package com.sitepark.ies.sharedkernel.security;

public interface EntityPermissions {

  Class<?> entityType();

  boolean create();

  boolean read();

  boolean write();

  boolean delete();
}

package com.sitepark.ies.sharedkernel.security;

public interface PasswordEncoder {

  String encode(String rawPassword);

  boolean matches(String rawPassword, String prefixEncodedPassword);

  default boolean upgradeEncoding(String prefixEncodedPassword) {
    return false;
  }
}

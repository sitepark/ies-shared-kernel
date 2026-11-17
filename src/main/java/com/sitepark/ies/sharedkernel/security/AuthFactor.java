package com.sitepark.ies.sharedkernel.security;

/** A second factor a user has set up, asked for after the password. */
public enum AuthFactor {
  /** One-time code of an authenticator app. */
  TOTP,
  /** One-time code sent to the e-mail address of the account. */
  EMAIL_CODE,
}

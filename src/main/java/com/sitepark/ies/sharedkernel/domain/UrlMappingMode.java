package com.sitepark.ies.sharedkernel.domain;

public enum UrlMappingMode {
  /** Alias: content is resolved internally and served directly (HTTP 200). */
  FORWARD(200, false),

  /** Permanent redirect – the URL has moved, SEO value is passed on. */
  REDIRECT_PERMANENT(301, true),

  /** Temporary redirect – target may change again, no SEO transfer. */
  REDIRECT_TEMPORARY(302, true),

  /** Temporary redirect that preserves the HTTP method and body (e.g. POST stays POST). */
  REDIRECT_TEMPORARY_PRESERVE_METHOD(307, true),

  /** Permanent redirect that preserves the HTTP method and body. */
  REDIRECT_PERMANENT_PRESERVE_METHOD(308, true);

  private final int httpStatus;
  private final boolean redirect;

  UrlMappingMode(int httpStatus, boolean redirect) {
    this.httpStatus = httpStatus;
    this.redirect = redirect;
  }

  public int getHttpStatus() {
    return httpStatus;
  }

  public boolean isRedirect() {
    return redirect;
  }

  public boolean isForward() {
    return !redirect;
  }

  public boolean isPermanent() {
    return this == REDIRECT_PERMANENT || this == REDIRECT_PERMANENT_PRESERVE_METHOD;
  }
}

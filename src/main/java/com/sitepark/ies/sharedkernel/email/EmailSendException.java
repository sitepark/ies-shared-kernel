package com.sitepark.ies.sharedkernel.email;

import java.io.Serial;

public class EmailSendException extends Exception {

  @Serial private static final long serialVersionUID = 1L;

  public EmailSendException(String message) {
    super(message);
  }

  public EmailSendException(String message, Throwable cause) {
    super(message, cause);
  }
}

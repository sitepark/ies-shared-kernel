package com.sitepark.ies.sharedkernel.email;

import java.io.Serial;

/**
 * Exception thrown when email sending fails.
 *
 * <p>This exception indicates failures during the email sending process,
 * which can include:
 * <ul>
 *   <li>Template rendering errors (template not found, invalid template syntax)</li>
 *   <li>Theme or data loading failures</li>
 *   <li>SMTP transport errors (connection failures, authentication issues)</li>
 *   <li>Message construction errors (invalid recipients, missing required fields)</li>
 * </ul>
 */
public class EmailSendException extends Exception {

  @Serial private static final long serialVersionUID = 1L;

  public EmailSendException(String message) {
    super(message);
  }

  public EmailSendException(String message, Throwable cause) {
    super(message, cause);
  }
}

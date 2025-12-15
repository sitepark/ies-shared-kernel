package com.sitepark.ies.sharedkernel.email;

import java.util.List;

/**
 * Represents email parameters that can be configured from outside the application server via API
 * interfaces.
 *
 * <p>This class encapsulates configurable parameters for email messaging, allowing precise control
 * over email notification settings provided through external API interfaces.
 */
public record ExternalEmailParameters(
    EmailAddress from, List<EmailAddress> replyTo, EmailMessageThemeIdentifier theme, String lang) {

  public ExternalEmailParameters {
    replyTo = replyTo == null ? List.of() : List.copyOf(replyTo);
  }

  public List<EmailAddress> replyTo() {
    return List.copyOf(replyTo);
  }
}

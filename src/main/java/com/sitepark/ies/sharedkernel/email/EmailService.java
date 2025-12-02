package com.sitepark.ies.sharedkernel.email;

import java.util.Map;

/**
 * Service interface for sending emails with template support.
 *
 * <p>This service handles both direct email sending with pre-rendered content
 * ({@link SimpleEmailMessage}) and template-based emails ({@link TemplateEmailMessage})
 * that use Mustache templates with theme support and multi-language capabilities.
 *
 * <p>The implementation is responsible for:
 * <ul>
 *   <li>Rendering template-based emails using Mustache templates</li>
 *   <li>Applying themes with language-specific customization</li>
 *   <li>Merging default data with runtime data</li>
 *   <li>Processing variables with format-specific handling (HTML vs plain text)</li>
 *   <li>Sending emails via configured transport (SMTP)</li>
 * </ul>
 */
public interface EmailService {
  /**
   * Sends an email.
   *
   * <p>If the email contains a {@link TemplateEmailMessage}, the service will:
   * <ol>
   *   <li>Load the template by message type</li>
   *   <li>Load language-specific default data and merge with runtime data</li>
   *   <li>Load the theme with language support</li>
   *   <li>Preprocess variables (separate processing for HTML and plain text)</li>
   *   <li>Render HTML and text templates using Mustache</li>
   *   <li>Extract subject from processed data</li>
   *   <li>Send via SMTP transport</li>
   * </ol>
   *
   * @param email the email to send with all metadata and content
   * @throws EmailSendException if sending fails due to transport errors or rendering issues
   */
  void send(Email email) throws EmailSendException;

  /**
   * Resolves a Mustache template string with the provided data.
   *
   * <p>This utility method allows rendering of Mustache templates outside
   * the normal email sending workflow. Useful for previewing templates or
   * processing individual template fragments.
   *
   * @param message the Mustache template string to resolve
   * @param data the data map containing variables for template substitution
   * @return the resolved string with all Mustache expressions replaced
   */
  String resolveMessage(String message, Map<String, Object> data);
}

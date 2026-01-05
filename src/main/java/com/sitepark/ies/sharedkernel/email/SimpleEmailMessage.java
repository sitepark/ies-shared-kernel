package com.sitepark.ies.sharedkernel.email;

/**
 * Email message with pre-rendered content.
 *
 * <p>Use this when email content is already rendered and does not require
 * template processing. This is the result type after rendering a
 * {@link TemplateEmailMessage}, or can be used directly for simple emails
 * that don't need template functionality.
 *
 * @param subject the email subject line
 * @param html the HTML body content
 * @param plain the plain text body content (fallback for clients without HTML support)
 */
public record SimpleEmailMessage(String subject, String html, String plain)
    implements EmailMessage {}

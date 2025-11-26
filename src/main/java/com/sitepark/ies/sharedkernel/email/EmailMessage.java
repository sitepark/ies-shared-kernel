package com.sitepark.ies.sharedkernel.email;

/**
 * Marker interface for email message types.
 *
 * <p>Implementations define different ways to construct email content:
 * <ul>
 *   <li>{@link SimpleEmailMessage} - Direct email with pre-rendered subject, HTML and plain text</li>
 *   <li>{@link TemplateEmailMessage} - Template-based email using Mustache templates with theme
 *       support and language-specific defaults</li>
 * </ul>
 */
public interface EmailMessage {}

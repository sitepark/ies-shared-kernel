package com.sitepark.ies.sharedkernel.email;

public record SimpleEmailMessage(String subject, String html, String plain)
    implements EmailMessage {}

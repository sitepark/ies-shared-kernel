package com.sitepark.ies.sharedkernel.email;

import java.util.Map;

public interface EmailService {
  void send(Email email) throws EmailSendException;

  String resolveMessage(String message, Map<String, Object> data);
}

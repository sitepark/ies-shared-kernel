package com.sitepark.ies.sharedkernel.backgroundoperation;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface Scheduler {

  boolean isStarted();

  void start();

  void stop();

  Optional<OffsetDateTime> nextRun();

  Optional<Exception> getLastError();
}

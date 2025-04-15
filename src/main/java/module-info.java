module com.sitepark.ies.shared.security {
  exports com.sitepark.ies.sharedkernel.base;
  exports com.sitepark.ies.sharedkernel.anchor.domain;
  exports com.sitepark.ies.sharedkernel.anchor.exception;
  exports com.sitepark.ies.sharedkernel.security.domain;
  exports com.sitepark.ies.sharedkernel.security.exceptions;

  requires org.apache.logging.log4j;
  requires com.github.spotbugs.annotations;
  requires transitive com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.annotation;

  opens com.sitepark.ies.sharedkernel.base;
  opens com.sitepark.ies.sharedkernel.anchor.domain;
  opens com.sitepark.ies.sharedkernel.security.domain;
}

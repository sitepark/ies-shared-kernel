module com.sitepark.ies.sharedkernel {
  exports com.sitepark.ies.sharedkernel.base;
  exports com.sitepark.ies.sharedkernel.anchor;
  exports com.sitepark.ies.sharedkernel.security;
  exports com.sitepark.ies.sharedkernel.security.annotation;
  exports com.sitepark.ies.sharedkernel.audit;
  exports com.sitepark.ies.sharedkernel.domain;
  exports com.sitepark.ies.sharedkernel.backgroundoperation;
  exports com.sitepark.ies.sharedkernel.patch;
  exports com.sitepark.ies.sharedkernel.json;
  exports com.sitepark.ies.sharedkernel.email;

  requires org.apache.logging.log4j;
  requires transitive com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.annotation;
  requires static com.github.spotbugs.annotations;
  requires static org.jetbrains.annotations;
  requires jsr305;

  opens com.sitepark.ies.sharedkernel.base;
  opens com.sitepark.ies.sharedkernel.anchor;
  opens com.sitepark.ies.sharedkernel.security;
  opens com.sitepark.ies.sharedkernel.audit;
  opens com.sitepark.ies.sharedkernel.domain;
}

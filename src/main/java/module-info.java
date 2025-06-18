module com.sitepark.ies.sharedkernel {
  exports com.sitepark.ies.sharedkernel.base;
  exports com.sitepark.ies.sharedkernel.anchor;
  exports com.sitepark.ies.sharedkernel.security;
  exports com.sitepark.ies.sharedkernel.security.annotation;
  exports com.sitepark.ies.sharedkernel.audit;

  requires org.apache.logging.log4j;
  requires static com.github.spotbugs.annotations;
  requires transitive com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.annotation;
  requires static org.jetbrains.annotations;

  opens com.sitepark.ies.sharedkernel.base;
  opens com.sitepark.ies.sharedkernel.anchor;
  opens com.sitepark.ies.sharedkernel.security;
  opens com.sitepark.ies.sharedkernel.audit;
}

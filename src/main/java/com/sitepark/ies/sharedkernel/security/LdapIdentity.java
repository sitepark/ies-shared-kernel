package com.sitepark.ies.sharedkernel.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * The <code>LdapIdentity</code> class represents an identity provider using LDAP for user
 * authentication. It facilitates user authentication and access control using LDAP credentials.
 */
@SuppressWarnings({"PMD.AvoidFieldNameMatchingMethodName"})
public final class LdapIdentity implements Identity {

  private final String serverId;

  private final String dn;

  @JsonCreator
  LdapIdentity(@JsonProperty("serverId") String serverId, @JsonProperty("dn") String dn) {
    Objects.requireNonNull(serverId, "serverId must not be null");
    Objects.requireNonNull(dn, "db must not be null");
    if (dn.isBlank()) {
      throw new IllegalArgumentException("dn must not be blank");
    }
    this.serverId = serverId;
    this.dn = dn;
  }

  @JsonProperty
  public String serverId() {
    return this.serverId;
  }

  @JsonProperty
  public String dn() {
    return this.dn;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.serverId, this.dn);
  }

  @Override
  public boolean equals(Object o) {

    if (!(o instanceof LdapIdentity that)) {
      return false;
    }

    return Objects.equals(this.serverId, that.serverId) && Objects.equals(this.dn, that.dn);
  }

  @Override
  public String toString() {
    return "LdapIdentity{" + "serverId=" + serverId + ", dn='" + dn + '\'' + '}';
  }
}

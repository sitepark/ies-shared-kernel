package com.sitepark.ies.sharedkernel.security;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * The <code>Identity</code> interface represents a user's identity for authentication purposes.
 * Classes like {@link LdapIdentity} implement this interface to specify how users can authenticate
 * themselves using different identity providers.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type",
    include = JsonTypeInfo.As.EXISTING_PROPERTY)
@JsonSubTypes({
  @JsonSubTypes.Type(value = LdapIdentity.class, name = "ldap"),
  @JsonSubTypes.Type(value = InternalIdentity.class, name = "internal")
})
@SuppressWarnings("PMD.ImplicitFunctionalInterface")
public interface Identity {

  static Identity internal() {
    return InternalIdentity.getInstance();
  }

  static Identity ldap(String serverId, String dn) {
    return new LdapIdentity(serverId, dn);
  }

  String getType();
}

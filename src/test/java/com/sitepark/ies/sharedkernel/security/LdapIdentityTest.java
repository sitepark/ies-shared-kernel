package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jparams.verifier.tostring.ToStringVerifier;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

@SuppressFBWarnings({
  "PI_DO_NOT_REUSE_PUBLIC_IDENTIFIERS_CLASS_NAMES",
  "NP_NULL_PARAM_DEREF_NONVIRTUAL",
  "NP_NULL_PARAM_DEREF_ALL_TARGETS_DANGEROUS"
})
class LdapIdentityTest {

  private static final String USER_DN = "userdn";

  @Test
  void testEquals() {
    EqualsVerifier.forClass(LdapIdentity.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(LdapIdentity.class).verify();
  }

  @Test
  void testSetServer() throws JsonProcessingException {
    LdapIdentity ldapIdentity = new LdapIdentity("2", USER_DN);
    assertEquals("2", ldapIdentity.serverId(), "unexpected server");
  }

  @Test
  void testSetInvalidServer() throws JsonProcessingException {
    assertThrows(
        NullPointerException.class,
        () -> {
          new LdapIdentity(null, USER_DN);
        });
  }

  @Test
  void testSetNullDn() throws JsonProcessingException {
    assertThrows(
        NullPointerException.class,
        () -> {
          new LdapIdentity("1", null);
        });
  }

  @Test
  void testSetBlankDn() throws JsonProcessingException {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          new LdapIdentity("1", " ");
        });
  }

  @Test
  void testSetDn() throws JsonProcessingException {
    LdapIdentity ldapIdentity = new LdapIdentity("2", USER_DN);
    assertEquals(USER_DN, ldapIdentity.dn(), "unexpected server");
  }

  @Test
  void testSerialize() throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

    LdapIdentity ldapIdentity = new LdapIdentity("2", USER_DN);

    String json = mapper.writeValueAsString(ldapIdentity);

    String expected = "{\"@type\":\"ldap\",\"serverId\":\"2\",\"dn\":\"userdn\"}";

    assertEquals(expected, json, "unexpected json");
  }

  @Test
  void testDeserialize() throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    mapper.registerModule(module);

    String json = "{\"@type\":\"ldap\",\"serverId\":\"2\",\"dn\":\"userdn\"}";

    LdapIdentity ldapIdentity = mapper.readValue(json, LdapIdentity.class);

    LdapIdentity expected = new LdapIdentity("2", USER_DN);

    assertEquals(expected, ldapIdentity, "unexpected ldapIdentity");
  }
}

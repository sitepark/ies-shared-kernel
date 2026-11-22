package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.json.JsonMapper;

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
  void testSetServer() {
    LdapIdentity ldapIdentity = new LdapIdentity("2", USER_DN);
    assertEquals("2", ldapIdentity.serverId(), "unexpected server");
  }

  @Test
  void testSetInvalidServer() {
    assertThrows(
        NullPointerException.class,
        () -> {
          new LdapIdentity(null, USER_DN);
        });
  }

  @Test
  void testSetNullDn() {
    assertThrows(
        NullPointerException.class,
        () -> {
          new LdapIdentity("1", null);
        });
  }

  @Test
  void testSetBlankDn() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          new LdapIdentity("1", " ");
        });
  }

  @Test
  void testSetDn() {
    LdapIdentity ldapIdentity = new LdapIdentity("2", USER_DN);
    assertEquals(USER_DN, ldapIdentity.dn(), "unexpected server");
  }

  @Test
  void testSerialize() {

    JsonMapper mapper =
        JsonMapper.builder()
            .disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
            .changeDefaultPropertyInclusion(
                inclusion -> inclusion.withValueInclusion(JsonInclude.Include.NON_EMPTY))
            .build();

    LdapIdentity ldapIdentity = new LdapIdentity("2", USER_DN);

    String json = mapper.writeValueAsString(ldapIdentity);

    String expected = "{\"serverId\":\"2\",\"dn\":\"userdn\",\"type\":\"ldap\"}";

    assertEquals(expected, json, "unexpected json");
  }

  @Test
  void testDeserialize() {

    JsonMapper mapper =
        JsonMapper.builder().disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY).build();

    String json = "{\"serverId\":\"2\",\"dn\":\"userdn\",\"type\":\"ldap\"}";

    LdapIdentity ldapIdentity = mapper.readValue(json, LdapIdentity.class);

    LdapIdentity expected = new LdapIdentity("2", USER_DN);

    assertEquals(expected, ldapIdentity, "unexpected ldapIdentity");
  }
}

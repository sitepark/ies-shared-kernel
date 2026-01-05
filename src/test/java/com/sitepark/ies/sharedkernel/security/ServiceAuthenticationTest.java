package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.jparams.verifier.tostring.ToStringVerifier;
import java.util.List;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class ServiceAuthenticationTest {

  @Test
  void testGetName() {
    ServiceAuthentication serviceAuthentication =
        ServiceAuthentication.builder().name("testPurpose").build();
    assertEquals("testPurpose", serviceAuthentication.name(), "name should return set value");
  }

  @Test
  void testGetPermissions() {
    Permission permission1 = mock();
    Permission permission2 = mock();
    ServiceAuthentication serviceAuthentication =
        ServiceAuthentication.builder()
            .name("testPurpose")
            .permissions(config -> config.addAll(permission1, permission2))
            .build();

    assertEquals(
        List.of(permission1, permission2),
        serviceAuthentication.permissions(),
        "permissions should return the configured permissions");
  }

  @Test
  void testBuilder() {
    ServiceAuthentication serviceAuthentication =
        ServiceAuthentication.builder().name("testPurpose").build();
    assertNotNull(serviceAuthentication, "builder should create valid instance");
  }

  @Test
  void testSetNullName() {
    assertThrows(
        NullPointerException.class,
        () -> ServiceAuthentication.builder().name(null),
        "name should not accept null");
  }

  @Test
  void testBuildWithoutName() {
    assertThrows(
        NullPointerException.class,
        () -> ServiceAuthentication.builder().build(),
        "build should require name to be set");
  }

  @Test
  void testNameWithEmptyString() {
    ServiceAuthentication serviceAuthentication = ServiceAuthentication.builder().name("").build();
    assertEquals("", serviceAuthentication.name(), "name should accept empty string");
  }

  @Test
  void testNameWithBlankString() {
    ServiceAuthentication serviceAuthentication = ServiceAuthentication.builder().name(" ").build();
    assertEquals(" ", serviceAuthentication.name(), "name should accept blank string");
  }

  @Test
  void testPermissionsDefaultValue() {
    ServiceAuthentication serviceAuthentication =
        ServiceAuthentication.builder().name("testPurpose").build();
    assertEquals(
        List.of(), serviceAuthentication.permissions(), "permissions should default to empty list");
  }

  @Test
  void testPermissionsWithEmptyConfigurer() {
    ServiceAuthentication serviceAuthentication =
        ServiceAuthentication.builder().name("testPurpose").permissions(config -> {}).build();
    assertEquals(
        List.of(),
        serviceAuthentication.permissions(),
        "permissions should be empty with empty configurer");
  }

  @Test
  void testPermissionsWithListParameter() {
    Permission permission1 = mock();
    Permission permission2 = mock();
    ServiceAuthentication serviceAuthentication =
        ServiceAuthentication.builder()
            .name("testPurpose")
            .permissions(List.of(permission1, permission2))
            .build();

    assertEquals(
        List.of(permission1, permission2),
        serviceAuthentication.permissions(),
        "permissions should accept List parameter");
  }

  @Test
  void testPermissionsWithEmptyList() {
    ServiceAuthentication serviceAuthentication =
        ServiceAuthentication.builder().name("testPurpose").permissions(List.of()).build();
    assertEquals(
        List.of(), serviceAuthentication.permissions(), "permissions should accept empty list");
  }

  @Test
  void testPermissionsReturnsDefensiveCopy() {
    ServiceAuthentication serviceAuthentication =
        ServiceAuthentication.builder().name("testPurpose").build();
    assertThrows(
        UnsupportedOperationException.class,
        () -> serviceAuthentication.permissions().add(mock()),
        "permissions should return immutable copy");
  }

  @Test
  void testMultiplePermissionsCallsWithConfigurer() {
    Permission permission1 = mock();
    Permission permission2 = mock();
    Permission permission3 = mock();

    ServiceAuthentication.Builder builder =
        ServiceAuthentication.builder()
            .name("testPurpose")
            .permissions(config -> config.addAll(permission1, permission2));
    builder.permissions(config -> config.add(permission3));

    assertEquals(
        List.of(permission3),
        builder.build().permissions(),
        "last permissions call should replace previous");
  }

  @Test
  void testMultiplePermissionsCallsWithList() {
    Permission permission1 = mock();
    Permission permission2 = mock();

    ServiceAuthentication.Builder builder =
        ServiceAuthentication.builder().name("testPurpose").permissions(List.of(permission1));
    builder.permissions(List.of(permission2));

    assertEquals(
        List.of(permission2),
        builder.build().permissions(),
        "last permissions call should replace previous");
  }

  @Test
  void testMultipleNameCalls() {
    ServiceAuthentication.Builder builder = ServiceAuthentication.builder();
    builder.name("first");
    builder.name("second");
    assertEquals("second", builder.build().name(), "last name call should win");
  }

  @Test
  void testBuilderReuse() {
    ServiceAuthentication.Builder builder = ServiceAuthentication.builder().name("testPurpose");

    ServiceAuthentication auth1 = builder.build();
    ServiceAuthentication auth2 = builder.build();

    assertEquals(auth1, auth2, "reusing builder should produce equal objects");
  }

  @Test
  void testPermissionsWithMultipleItems() {
    Permission permission1 = mock();
    Permission permission2 = mock();
    Permission permission3 = mock();

    ServiceAuthentication serviceAuthentication =
        ServiceAuthentication.builder()
            .name("testPurpose")
            .permissions(config -> config.add(permission1).add(permission2).add(permission3))
            .build();

    assertEquals(
        List.of(permission1, permission2, permission3),
        serviceAuthentication.permissions(),
        "permissions should contain all added items");
  }

  @Test
  void testBuilderChaining() {
    Permission permission = mock();
    ServiceAuthentication serviceAuthentication =
        ServiceAuthentication.builder()
            .name("testPurpose")
            .permissions(config -> config.add(permission))
            .build();

    assertNotNull(serviceAuthentication, "builder chaining should produce valid object");
  }

  @Test
  void testEquals() {
    EqualsVerifier.forClass(ServiceAuthentication.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(ServiceAuthentication.class).verify();
  }

  @Test
  void testEqualsSameNameAndPermissions() {
    Permission permission = mock();

    ServiceAuthentication auth1 =
        ServiceAuthentication.builder()
            .name("testPurpose")
            .permissions(config -> config.add(permission))
            .build();

    ServiceAuthentication auth2 =
        ServiceAuthentication.builder()
            .name("testPurpose")
            .permissions(config -> config.add(permission))
            .build();

    assertEquals(auth1, auth2, "objects with same name and permissions should be equal");
  }

  @Test
  void testPermissionsWithSingleAdd() {
    Permission permission = mock();
    ServiceAuthentication serviceAuthentication =
        ServiceAuthentication.builder()
            .name("testPurpose")
            .permissions(config -> config.add(permission))
            .build();

    assertEquals(
        List.of(permission),
        serviceAuthentication.permissions(),
        "permissions should contain single added permission");
  }

  @Test
  void testMixedPermissionsCalls() {
    Permission permission1 = mock();
    Permission permission2 = mock();

    ServiceAuthentication.Builder builder =
        ServiceAuthentication.builder().name("testPurpose").permissions(List.of(permission1));

    builder.permissions(config -> config.add(permission2));

    assertEquals(
        List.of(permission2),
        builder.build().permissions(),
        "configurer call should replace list call");
  }
}

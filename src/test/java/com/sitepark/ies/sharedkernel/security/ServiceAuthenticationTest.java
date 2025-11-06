package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class ServiceAuthenticationTest {
  @Test
  void testGetName() {
    ServiceAuthentication serviceAuthentication =
        ServiceAuthentication.builder().name("testPurpose").build();
    assertEquals("testPurpose", serviceAuthentication.name(), "getName should return 'System'");
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
        "getPermissions should return the permissions passed to the constructor");
  }
}

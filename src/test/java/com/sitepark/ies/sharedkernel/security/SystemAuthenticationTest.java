package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class SystemAuthenticationTest {
  @Test
  void testGetName() {
    SystemAuthentication systemAuthentication = new SystemAuthentication("testPurpose");
    assertEquals("System", systemAuthentication.getName(), "getName should return 'System'");
  }

  @Test
  void testGetPurpose() {
    String purpose = "testPurpose";
    SystemAuthentication systemAuthentication = new SystemAuthentication(purpose);
    assertEquals(
        purpose,
        systemAuthentication.getPurpose(),
        "getPurpose should return the purpose set in the constructor");
  }

  @Test
  void testGetPermissions() {
    Permission permission1 = mock();
    Permission permission2 = mock();
    SystemAuthentication systemAuthentication =
        new SystemAuthentication("testPurpose", permission1, permission2);

    assertEquals(
        List.of(permission1, permission2),
        systemAuthentication.getPermissions(),
        "getPermissions should return the permissions passed to the constructor");
  }
}

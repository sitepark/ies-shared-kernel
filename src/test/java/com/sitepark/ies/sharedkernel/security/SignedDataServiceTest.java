package com.sitepark.ies.sharedkernel.security;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Map;
import org.junit.jupiter.api.Test;

class SignedDataServiceTest {

  @Test
  void testSignUsesDefaultTtl() {
    SignedDataService service = mock(SignedDataService.class, CALLS_REAL_METHODS);
    SignedData expected =
        new SignedData(
            "token", Instant.parse("2026-01-01T00:00:00Z"), Instant.parse("2026-01-08T00:00:00Z"));
    when(service.sign(eq("purpose"), any(), eq(SignedDataService.DEFAULT_TTL)))
        .thenReturn(expected);

    assertSame(
        expected,
        service.sign("purpose", Map.of("a", "b")),
        "sign without ttl should delegate with DEFAULT_TTL");
  }
}

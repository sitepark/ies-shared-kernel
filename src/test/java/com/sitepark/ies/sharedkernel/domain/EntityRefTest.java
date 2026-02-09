package com.sitepark.ies.sharedkernel.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntityRefTest {
  private EntityRef entityRef;

  @BeforeEach
  void setUp() {
    this.entityRef = EntityRef.of("user", "1234");
  }

  @Test
  void testSerialize() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(this.entityRef);

    String expected =
        """
        {"type":"user","id":"1234"}\
        """;

    assertEquals(expected, json, "Serialized JSON should match expected format");
  }

  @Test
  void testDeserialize() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    String json =
        """
        {"type":"user","id":"1234"}\
        """;
    EntityRef entityRef = mapper.readValue(json, EntityRef.class);

    assertEquals(this.entityRef, entityRef, "Deserialized User should match original User");
  }
}

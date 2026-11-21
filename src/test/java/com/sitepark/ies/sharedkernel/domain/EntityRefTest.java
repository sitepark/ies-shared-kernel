package com.sitepark.ies.sharedkernel.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.json.JsonMapper;

class EntityRefTest {
  private EntityRef entityRef;

  @BeforeEach
  void setUp() {
    this.entityRef = EntityRef.of("user", "1234");
  }

  @Test
  void testSerialize() {
    JsonMapper mapper =
        JsonMapper.builder().disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY).build();
    String json = mapper.writeValueAsString(this.entityRef);

    String expected =
        """
        {"type":"user","id":"1234"}\
        """;

    assertEquals(expected, json, "Serialized JSON should match expected format");
  }

  @Test
  void testDeserialize() {
    JsonMapper mapper =
        JsonMapper.builder().disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY).build();
    String json =
        """
        {"type":"user","id":"1234"}\
        """;
    EntityRef entityRef = mapper.readValue(json, EntityRef.class);

    assertEquals(this.entityRef, entityRef, "Deserialized User should match original User");
  }
}

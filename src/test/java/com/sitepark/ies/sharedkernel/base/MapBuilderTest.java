package com.sitepark.ies.sharedkernel.base;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.Test;

class MapBuilderTest {
  @Test
  void testSetWithVarargs() {
    MapBuilder<String, String> builder = new MapBuilder<>();
    Map<String, String> result = builder.set(entry("a", "123"), entry("b", "456")).build();

    Map<String, String> expected = Map.of("a", "123", "b", "456");

    assertEquals(expected, result, "Should set entries from varargs");
  }

  @Test
  void testSetWithMap() {
    MapBuilder<String, String> builder = new MapBuilder<>();
    Map<String, String> map = Map.of("a", "111", "b", "222");
    Map<String, String> result = builder.set(map).build();

    Map<String, String> expected = Map.of("a", "111", "b", "222");

    assertEquals(expected, result, "Should set entries from map");
  }

  @Test
  void testSetEntriesWithNull() {
    MapBuilder<String, String> builder = new MapBuilder<>();
    Map<String, String> result = builder.set((Map.Entry<String, String>[]) null).build();

    assertTrue(result.isEmpty(), "Should ignore null varargs and result in empty map");
  }

  @Test
  void testSetEntryWithNull() {
    MapBuilder<String, String> builder = new MapBuilder<>();
    Map<String, String> result = builder.set((Map.Entry<String, String>) null).build();

    assertTrue(result.isEmpty(), "Should ignore null varargs and result in empty map");
  }

  @Test
  void testSetMapWithNull() {
    MapBuilder<String, String> builder = new MapBuilder<>();
    Map<String, String> result = builder.set((Map<String, String>) null).build();

    assertTrue(result.isEmpty(), "Should ignore null map and result in empty map");
  }

  @Test
  void testPut() {
    MapBuilder<String, String> builder = new MapBuilder<>();
    Map<String, String> result = builder.put("a", "123").put("b", "456").build();

    Map<String, String> expected = Map.of("a", "123", "b", "456");

    assertEquals(expected, result, "Should put entries individually");
  }

  @Test
  void testPutNull() {
    MapBuilder<String, String> builder = new MapBuilder<>();
    builder.put("a", "777");
    Map<String, String> result = builder.put("b", null).build();

    Map<String, String> expected = Map.of("a", "777");

    assertEquals(expected, result, "Should ignore null value and keep existing entries");
  }

  @Test
  void testPutAllWith() {
    MapBuilder<String, String> builder = new MapBuilder<>();
    Map<String, String> map = Map.of("a", "aaa", "b", "bbb");
    Map<String, String> result = builder.putAll(map).build();

    Map<String, String> expected = Map.of("a", "aaa", "b", "bbb");

    assertEquals(expected, result, "Should put all entries from map");
  }

  @Test
  void testPutAllWithNull() {
    MapBuilder<String, String> builder = new MapBuilder<>();
    Map<String, String> result = builder.putAll((Map<String, String>) null).build();

    assertTrue(result.isEmpty(), "Should ignore null map and result in empty map");
  }

  @Test
  void testBuildEmptyList() {
    MapBuilder<String, String> builder = new MapBuilder<>();
    Map<String, String> result = builder.build();

    assertTrue(result.isEmpty(), "Built map should be empty when no items were added");
  }

  @Test
  void testSetMethodClearsPreviousItems() {
    MapBuilder<String, String> builder = new MapBuilder<>();
    builder.put("a", "1").put("b", "2");
    Map<String, String> result = builder.set(entry("c", "3"), entry("d", "4")).build();

    Map<String, String> expected = Map.of("c", "3", "d", "4");

    assertEquals(expected, result, "Set method should clear previous items");
  }

  @Test
  void testChainedOperations() {
    MapBuilder<String, String> builder = new MapBuilder<>();
    Map<String, String> result =
        builder.put("a", "100").putAll(Map.of("b", "200", "c", "300")).put("d", "400").build();

    Map<String, String> expected = Map.of("a", "100", "b", "200", "c", "300", "d", "400");

    assertEquals(expected, result, "Should handle chained operations correctly");
  }

  @Test
  void testBuildReturnsImmutableCopy() {
    MapBuilder<String, String> builder = new MapBuilder<>();
    builder.put("a", "1").put("b", "2");
    Map<String, String> firstBuild = builder.build();
    builder.put("c", "3");

    Map<String, String> expectedFirst = Map.of("a", "1", "b", "2");

    assertEquals(
        expectedFirst,
        firstBuild,
        "First build should not be affected by subsequent modifications");
  }
}

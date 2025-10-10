package com.sitepark.ies.sharedkernel.base;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class ListBuilderTest {

  @Test
  void testSetWithVarargs() {
    ListBuilder<String> builder = new ListBuilder<>();
    List<String> result = builder.set("123", "456").build();

    List<String> expected = List.of("123", "456");

    assertEquals(expected, result, "Should set items from varargs");
  }

  @Test
  void testSetWithVarargsNull() {
    ListBuilder<String> builder = new ListBuilder<>();
    builder.add("999");
    List<String> result = builder.set((String[]) null).build();

    List<String> expected = List.of("999");

    assertEquals(expected, result, "Should ignore null varargs and keep existing items");
  }

  @Test
  void testSetWithCollection() {
    ListBuilder<String> builder = new ListBuilder<>();
    List<String> strings = List.of("111", "222");
    List<String> result = builder.set(strings).build();

    List<String> expected = List.of("111", "222");

    assertEquals(expected, result, "Should set items from collection");
  }

  @Test
  void testSetWithCollectionNull() {
    ListBuilder<String> builder = new ListBuilder<>();
    builder.add("888");
    List<String> result = builder.set((List<String>) null).build();

    List<String> expected = List.of("888");

    assertEquals(expected, result, "Should ignore null collection and keep existing items");
  }

  @Test
  void testAdd() {
    ListBuilder<String> builder = new ListBuilder<>();
    List<String> result = builder.add("123").add("456").build();

    List<String> expected = List.of("123", "456");

    assertEquals(expected, result, "Should add items individually");
  }

  @Test
  void testAddNull() {
    ListBuilder<String> builder = new ListBuilder<>();
    builder.add("777");
    List<String> result = builder.add(null).build();

    List<String> expected = List.of("777");

    assertEquals(expected, result, "Should ignore null item and keep existing items");
  }

  @Test
  void testAddAllWithVarargs() {
    ListBuilder<String> builder = new ListBuilder<>();
    List<String> result = builder.addAll("100", "200", "300").build();

    List<String> expected = List.of("100", "200", "300");

    assertEquals(expected, result, "Should add all items from varargs");
  }

  @Test
  void testAddAllWithVarargsNull() {
    ListBuilder<String> builder = new ListBuilder<>();
    builder.add("666");
    List<String> result = builder.addAll((String[]) null).build();

    List<String> expected = List.of("666");

    assertEquals(expected, result, "Should ignore null varargs and keep existing items");
  }

  @Test
  void testAddAllWithCollection() {
    ListBuilder<String> builder = new ListBuilder<>();
    List<String> strings = List.of("aaa", "bbb");
    List<String> result = builder.addAll(strings).build();

    List<String> expected = List.of("aaa", "bbb");

    assertEquals(expected, result, "Should add all items from collection");
  }

  @Test
  void testAddAllWithCollectionNull() {
    ListBuilder<String> builder = new ListBuilder<>();
    builder.add("555");
    List<String> result = builder.addAll((List<String>) null).build();

    List<String> expected = List.of("555");

    assertEquals(expected, result, "Should ignore null collection and keep existing items");
  }

  @Test
  void testBuildEmptyList() {
    ListBuilder<String> builder = new ListBuilder<>();
    List<String> result = builder.build();

    assertTrue(result.isEmpty(), "Should build empty list when no items added");
  }

  @Test
  void testSetMethodClearsPreviousItems() {
    ListBuilder<String> builder = new ListBuilder<>();
    builder.add("1").add("2");
    List<String> result = builder.set("3", "4").build();

    List<String> expected = List.of("3", "4");

    assertEquals(expected, result, "Should clear previous items when using set method");
  }

  @Test
  void testChainedOperations() {
    ListBuilder<String> builder = new ListBuilder<>();
    List<String> result = builder.add("100").addAll("200", "300").add("400").build();

    List<String> expected = List.of("100", "200", "300", "400");

    assertEquals(expected, result, "Should support chaining different builder methods");
  }

  @Test
  void testBuildReturnsImmutableCopy() {
    ListBuilder<String> builder = new ListBuilder<>();
    builder.add("1").add("2");
    List<String> firstBuild = builder.build();
    builder.add("3");

    List<String> expectedFirst = List.of("1", "2");

    assertEquals(expectedFirst, firstBuild, "First build should contain only first two items");
  }
}

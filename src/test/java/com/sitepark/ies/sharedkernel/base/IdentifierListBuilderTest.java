package com.sitepark.ies.sharedkernel.base;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sitepark.ies.sharedkernel.anchor.Anchor;
import java.util.List;
import org.junit.jupiter.api.Test;

class IdentifierListBuilderTest {

  @Test
  void testSetWithStringVarargs() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    List<Identifier> result = builder.set("123", "456", "test-anchor").build();

    List<Identifier> expected =
        List.of(Identifier.ofId("123"), Identifier.ofId("456"), Identifier.ofAnchor("test-anchor"));

    assertEquals(expected, result, "Should set identifiers from string varargs");
  }

  @Test
  void testSetWithStringVarargsNull() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    builder.id("999");
    List<Identifier> result = builder.set((String[]) null).build();

    List<Identifier> expected = List.of(Identifier.ofId("999"));

    assertEquals(expected, result, "Should ignore null varargs and keep existing identifiers");
  }

  @Test
  void testSetWithStringCollection() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    List<String> strings = List.of("111", "222", "anchor-test");
    List<Identifier> result = builder.set(strings).build();

    List<Identifier> expected =
        List.of(Identifier.ofId("111"), Identifier.ofId("222"), Identifier.ofAnchor("anchor-test"));

    assertEquals(expected, result, "Should set identifiers from string collection");
  }

  @Test
  void testSetWithStringCollectionNull() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    builder.id("888");
    List<Identifier> result = builder.set((List<String>) null).build();

    List<Identifier> expected = List.of(Identifier.ofId("888"));

    assertEquals(expected, result, "Should ignore null collection and keep existing identifiers");
  }

  @Test
  void testAddWithString() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    List<Identifier> result = builder.add("123").add("test-anchor").build();

    List<Identifier> expected = List.of(Identifier.ofId("123"), Identifier.ofAnchor("test-anchor"));

    assertEquals(expected, result, "Should add identifiers from strings");
  }

  @Test
  void testAddWithStringNull() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    builder.id("777");
    List<Identifier> result = builder.add(null).build();

    List<Identifier> expected = List.of(Identifier.ofId("777"));

    assertEquals(expected, result, "Should ignore null string and keep existing identifiers");
  }

  @Test
  void testIdentifiersWithVarargs() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    Identifier id1 = Identifier.ofId("100");
    Identifier id2 = Identifier.ofAnchor("anchor1");
    List<Identifier> result = builder.identifiers(id1, id2).build();

    List<Identifier> expected = List.of(id1, id2);

    assertEquals(expected, result, "Should set identifiers from varargs");
  }

  @Test
  void testIdentifiersWithVarargsNull() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    builder.id("666");
    List<Identifier> result = builder.identifiers((Identifier[]) null).build();

    List<Identifier> expected = List.of(Identifier.ofId("666"));

    assertEquals(expected, result, "Should ignore null identifier varargs and keep existing");
  }

  @Test
  void testIdentifiersWithCollection() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    Identifier id1 = Identifier.ofId("200");
    Identifier id2 = Identifier.ofAnchor("anchor2");
    List<Identifier> identifiers = List.of(id1, id2);
    List<Identifier> result = builder.identifiers(identifiers).build();

    List<Identifier> expected = List.of(id1, id2);

    assertEquals(expected, result, "Should set identifiers from collection");
  }

  @Test
  void testIdentifiersWithCollectionNull() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    builder.id("555");
    List<Identifier> result = builder.identifiers((List<Identifier>) null).build();

    List<Identifier> expected = List.of(Identifier.ofId("555"));

    assertEquals(expected, result, "Should ignore null identifier collection and keep existing");
  }

  @Test
  void testIdentifier() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    Identifier id1 = Identifier.ofId("300");
    Identifier id2 = Identifier.ofAnchor("anchor3");
    List<Identifier> result = builder.identifier(id1).identifier(id2).build();

    List<Identifier> expected = List.of(id1, id2);

    assertEquals(expected, result, "Should add identifiers individually");
  }

  @Test
  void testIdentifierNull() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    builder.id("444");
    List<Identifier> result = builder.identifier(null).build();

    List<Identifier> expected = List.of(Identifier.ofId("444"));

    assertEquals(expected, result, "Should ignore null identifier and keep existing");
  }

  @Test
  void testIdsWithVarargs() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    List<Identifier> result = builder.ids("400", "500", "600").build();

    List<Identifier> expected =
        List.of(Identifier.ofId("400"), Identifier.ofId("500"), Identifier.ofId("600"));

    assertEquals(expected, result, "Should set IDs from varargs");
  }

  @Test
  void testIdsWithVarargsNull() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    builder.id("333");
    List<Identifier> result = builder.ids((String[]) null).build();

    List<Identifier> expected = List.of(Identifier.ofId("333"));

    assertEquals(expected, result, "Should ignore null ID varargs and keep existing");
  }

  @Test
  void testIdsWithCollection() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    List<String> ids = List.of("700", "800", "900");
    List<Identifier> result = builder.ids(ids).build();

    List<Identifier> expected =
        List.of(Identifier.ofId("700"), Identifier.ofId("800"), Identifier.ofId("900"));

    assertEquals(expected, result, "Should set IDs from collection");
  }

  @Test
  void testIdsWithCollectionNull() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    builder.id("222");
    List<Identifier> result = builder.ids((List<String>) null).build();

    List<Identifier> expected = List.of(Identifier.ofId("222"));

    assertEquals(expected, result, "Should ignore null ID collection and keep existing");
  }

  @Test
  void testId() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    List<Identifier> result = builder.id("1000").id("1001").build();

    List<Identifier> expected = List.of(Identifier.ofId("1000"), Identifier.ofId("1001"));

    assertEquals(expected, result, "Should add IDs individually");
  }

  @Test
  void testIdNull() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    builder.id("111");
    List<Identifier> result = builder.id(null).build();

    List<Identifier> expected = List.of(Identifier.ofId("111"));

    assertEquals(expected, result, "Should ignore null ID and keep existing");
  }

  @Test
  void testIdBlank() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    builder.id("111");
    List<Identifier> result = builder.id("  ").build();

    List<Identifier> expected = List.of(Identifier.ofId("111"));

    assertEquals(expected, result, "Should ignore blank ID and keep existing");
  }

  @Test
  void testAnchorsWithVarargs() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    Anchor anchor1 = Anchor.ofString("anchor-a");
    Anchor anchor2 = Anchor.ofString("anchor-b");
    List<Identifier> result = builder.anchors(anchor1, anchor2).build();

    List<Identifier> expected = List.of(Identifier.ofAnchor(anchor1), Identifier.ofAnchor(anchor2));

    assertEquals(expected, result, "Should set anchors from varargs");
  }

  @Test
  void testAnchorsWithVarargsNull() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    builder.id("100");
    List<Identifier> result = builder.anchors((Anchor[]) null).build();

    List<Identifier> expected = List.of(Identifier.ofId("100"));

    assertEquals(expected, result, "Should ignore null anchor varargs and keep existing");
  }

  @Test
  void testAnchorsWithCollection() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    Anchor anchor1 = Anchor.ofString("anchor-x");
    Anchor anchor2 = Anchor.ofString("anchor-y");
    List<Anchor> anchors = List.of(anchor1, anchor2);
    List<Identifier> result = builder.anchors(anchors).build();

    List<Identifier> expected = List.of(Identifier.ofAnchor(anchor1), Identifier.ofAnchor(anchor2));

    assertEquals(expected, result, "Should set anchors from collection");
  }

  @Test
  void testAnchorsWithCollectionNull() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    builder.id("200");
    List<Identifier> result = builder.anchors((List<Anchor>) null).build();

    List<Identifier> expected = List.of(Identifier.ofId("200"));

    assertEquals(expected, result, "Should ignore null anchor collection and keep existing");
  }

  @Test
  void testAnchor() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    Anchor anchor1 = Anchor.ofString("first-anchor");
    Anchor anchor2 = Anchor.ofString("second-anchor");
    List<Identifier> result = builder.anchor(anchor1).anchor(anchor2).build();

    List<Identifier> expected = List.of(Identifier.ofAnchor(anchor1), Identifier.ofAnchor(anchor2));

    assertEquals(expected, result, "Should add anchors individually");
  }

  @Test
  void testAnchorNull() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    builder.id("300");
    List<Identifier> result = builder.anchor(null).build();

    List<Identifier> expected = List.of(Identifier.ofId("300"));

    assertEquals(expected, result, "Should ignore null anchor and keep existing");
  }

  @Test
  void testBuildEmptyList() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    List<Identifier> result = builder.build();

    assertTrue(result.isEmpty(), "Should build empty list when no identifiers added");
  }

  @Test
  void testSetMethodsClearPreviousIdentifiers() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    builder.id("1").id("2");
    List<Identifier> result = builder.set("3", "4").build();

    List<Identifier> expected = List.of(Identifier.ofId("3"), Identifier.ofId("4"));

    assertEquals(expected, result, "Should clear previous identifiers when using set method");
  }

  @Test
  void testChainedOperations() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    Anchor anchor = Anchor.ofString("mixed-anchor");
    List<Identifier> result =
        builder.id("100").anchor(anchor).add("200").identifier(Identifier.ofId("300")).build();

    List<Identifier> expected =
        List.of(
            Identifier.ofId("100"),
            Identifier.ofAnchor(anchor),
            Identifier.ofId("200"),
            Identifier.ofId("300"));

    assertEquals(expected, result, "Should support chaining different builder methods");
  }

  @Test
  void testBuildReturnsImmutableCopy() {
    IdentifierListBuilder builder = new IdentifierListBuilder();
    builder.id("1").id("2");
    List<Identifier> firstBuild = builder.build();
    builder.id("3");

    List<Identifier> expectedFirst = List.of(Identifier.ofId("1"), Identifier.ofId("2"));

    assertEquals(expectedFirst, firstBuild, "First build should contain only first two IDs");
  }
}

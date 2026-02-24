package com.sitepark.ies.sharedkernel.base;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jparams.verifier.tostring.ToStringVerifier;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

@SuppressFBWarnings("NP_NULL_PARAM_DEREF_ALL_TARGETS_DANGEROUS")
class UpdatableTest {

  @Test
  void testEquals() {
    EqualsVerifier.forClass(Updatable.class).verify();
  }

  @Test
  void testToString() {
    ToStringVerifier.forClass(Updatable.class).verify();
  }

  @Test
  void testUnchangedShouldNotUpdate() {
    assertFalse(
        Updatable.unchanged().shouldUpdate(), "Unchanged updatable should not require update");
  }

  @Test
  void testUnchangedGetValueIsNull() {
    assertNull(Updatable.unchanged().getValue(), "Unchanged updatable should have null value");
  }

  @Test
  void testOfWithValueShouldUpdate() {
    assertTrue(
        Updatable.of("test").shouldUpdate(), "Updatable created with value should require update");
  }

  @Test
  void testOfWithValueGetValue() {
    assertEquals(
        "test",
        Updatable.of("test").getValue(),
        "Updatable should return the value it was created with");
  }

  @Test
  void testOfWithNullShouldUpdate() {
    assertTrue(
        Updatable.of(null).shouldUpdate(),
        "Updatable created with null should still require update");
  }

  @Test
  void testOfWithNullGetValueIsNull() {
    assertNull(
        Updatable.of(null).getValue(), "Updatable created with null should return null value");
  }

  @Test
  void testIfUpdateRequiredInvokesActionWhenShouldUpdate() {
    AtomicBoolean actionInvoked = new AtomicBoolean(false);
    Updatable.of("test").ifUpdateRequired(value -> actionInvoked.set(true));
    assertTrue(actionInvoked.get(), "Action should be invoked when update is required");
  }

  @Test
  void testIfUpdateRequiredPassesValueToAction() {
    AtomicReference<String> captured = new AtomicReference<>();
    Updatable.of("hello").ifUpdateRequired(captured::set);
    assertEquals("hello", captured.get(), "Action should receive the correct value");
  }

  @Test
  void testIfUpdateRequiredDoesNotInvokeActionWhenUnchanged() {
    AtomicBoolean actionInvoked = new AtomicBoolean(false);
    Updatable.<String>unchanged().ifUpdateRequired(value -> actionInvoked.set(true));
    assertFalse(actionInvoked.get(), "Action should not be invoked when update is not required");
  }

  @Test
  void testIfUpdateRequiredPassesNullValueToAction() {
    AtomicReference<String> captured = new AtomicReference<>("not-null");
    Updatable.<String>of(null).ifUpdateRequired(captured::set);
    assertNull(captured.get(), "Action should receive null when updatable was created with null");
  }

  @Test
  void testToOptionalWithValue() {
    assertEquals(
        Optional.of("test"),
        Updatable.of("test").toOptional(),
        "toOptional should return Optional containing the value");
  }

  @Test
  void testToOptionalWithNullValue() {
    assertEquals(
        Optional.empty(),
        Updatable.<String>of(null).toOptional(),
        "toOptional with null value should return empty Optional");
  }

  @Test
  void testToOptionalWhenUnchanged() {
    assertEquals(
        Optional.empty(),
        Updatable.<String>unchanged().toOptional(),
        "toOptional when unchanged should return empty Optional");
  }
}

package com.sitepark.ies.sharedkernel.patch;

public interface PatchService<T> {

  /**
   * Calculates differences between two objects
   *
   * @param original Original object
   * @param modified Modified object
   * @return Patch representation of the changes
   */
  PatchDocument createPatch(T original, T modified);

  /**
   * Applies a patch to an object
   *
   * @param original Original object
   * @param patch Patch to be applied
   * @return Modified object
   */
  T applyPatch(T original, PatchDocument patch);

  /**
   * Parses a JSON representation of a patch
   *
   * @param json JSON representation of a patch
   * @return PatchDocument
   */
  PatchDocument parsePatch(String json);

  /**
   * Checks whether changes exist
   *
   * @param patch Patch to be checked
   * @return true if changes exist
   */
  boolean hasChanges(PatchDocument patch);
}

package com.sitepark.ies.sharedkernel.patch;

import java.util.List;

public interface PatchDocument {
  /**
   * Serializes a patch to JSON
   *
   * @return JSON representation of the patch
   */
  String toJson();

  /**
   * List of changes
   *
   * @return List of patch operations
   */
  List<PatchOperation> getOperations();

  /**
   * Checks if the patch document contains no operations
   *
   * @return true if no operations exist
   */
  boolean isEmpty();
}

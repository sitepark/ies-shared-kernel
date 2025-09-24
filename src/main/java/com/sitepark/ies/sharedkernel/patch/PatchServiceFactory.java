package com.sitepark.ies.sharedkernel.patch;

@SuppressWarnings("PMD.ImplicitFunctionalInterface")
public interface PatchServiceFactory {
  /**
   * Creates PatchService for a specific type
   *
   * @param type Object type
   * @return PatchService for this type
   */
  <T> PatchService<T> createPatchService(Class<T> type);
}

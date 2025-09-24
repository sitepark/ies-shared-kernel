package com.sitepark.ies.sharedkernel.patch;

public record PatchOperation(PatchOperationType type, String path, Object value) {}

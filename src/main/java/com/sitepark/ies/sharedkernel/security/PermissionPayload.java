package com.sitepark.ies.sharedkernel.security;

import com.sitepark.ies.sharedkernel.json.RawJson;
import javax.annotation.concurrent.Immutable;

@Immutable
public record PermissionPayload(String type, @RawJson String data) {}

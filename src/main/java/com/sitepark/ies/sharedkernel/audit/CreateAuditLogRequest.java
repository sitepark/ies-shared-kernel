package com.sitepark.ies.sharedkernel.audit;

import java.time.Instant;

/**
 * Represents a single audit entry describing a domain-relevant change within the system.
 *
 * <p>An {@code AuditLog} captures a state change that occurred on a specific domain entity, such as
 * a user, role, or privilege, and is typically emitted by a bounded context as part of a business
 * operation. Audit logs are domain-agnostic and do not contain any logic themselves— they serve as
 * traceable, interpretable records for inspection, compliance, and potential reversal.
 *
 * <p>This record contains metadata about the change, including:
 *
 * <ul>
 *   <li>the affected entity type and identifier
 *   <li>the nature of the action performed (e.g. create, update, remove)
 *   <li>the previous and new state in serialized form (usually JSON)
 *   <li>information about the user and authority responsible for the change
 *   <li>an optional batch ID linking this entry to a group of related changes
 * </ul>
 *
 * <p><strong>Typical use cases include:</strong>
 *
 * <ul>
 *   <li>Auditable change history for sensitive data
 *   <li>Undo support for reversible business operations
 *   <li>Monitoring and reporting across bounded contexts
 * </ul>
 *
 * @param entityType The type of the affected domain entity (e.g., USER, ROLE, PRIVILEGE)
 * @param entityId The identifier of the affected entity (may be numeric, UUID, or composite as
 *     string)
 * @param action The action performed on the entity (e.g., CREATE, REMOVE, REVOKE_PRIVILEGES)
 * @param backwardData A serialized representation of the previous state (diff or full), must not be
 *     {@code null}
 * @param forwardData A serialized representation of the resulting state (diff or full), must not be
 *     {@code null}
 * @param changedAt The timestamp when the change was recorded
 * @param parentId An optional identifier linking this entry to a parent log entry
 */
public record CreateAuditLogRequest(
    String entityType,
    String entityId,
    String entityName,
    String action,
    String backwardData,
    String forwardData,
    Instant changedAt,
    String parentId) {}

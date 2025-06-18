package com.sitepark.ies.sharedkernel.audit;

import java.time.OffsetDateTime;

/**
 * Represents a group of related audit log entries that were created as part of a single user or
 * system action.
 *
 * <p>An {@code AuditBatch} provides contextual information for one or more {@code AuditLog} entries
 * that belong together semantically — for example, the removal of multiple users or the assignment
 * of several privileges in a single operation.
 *
 * <p>This entity allows the system to:
 *
 * <ul>
 *   <li>Group and label changes under a common description
 *   <li>Track when and by whom the action was triggered
 *   <li>Support structured undo or analysis for entire change sets
 * </ul>
 *
 * <p>The {@code id} typically matches the {@code batchId} field in {@code AuditLog} entries and
 * serves as a unique identifier across the system.
 *
 * @param description Human-readable description of the batch action
 * @param createdAt Timestamp indicating when the batch was created
 * @param changedByUserId ID of the user who initiated the batch action
 * @param changedByAuthorityName Name of the authority or system acting on behalf of the user
 */
public record CreateAuditBatchCommand(
    String description,
    OffsetDateTime createdAt,
    String changedByUserId,
    String changedByAuthorityName) {}

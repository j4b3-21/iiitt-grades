package example.example.grading_engine.model.entity;


import example.example.grading_engine.enums.auditlogging.AuditAction;
import example.example.grading_engine.enums.auditlogging.AuditEntityType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "audit_log")
public class AuditLog {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private User actor;

    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false)
    private AuditAction action;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false)
    private AuditEntityType entityType;

    @Column(name = "entity_id", columnDefinition = "uuid")
    private UUID entityId;

    @Column(name = "previous_state", columnDefinition = "jsonb")
    private String previousState;

    @Column(name = "new_state", columnDefinition = "jsonb")
    private String newState;

    @Column(name = "event_time", nullable = false)
    private LocalDateTime eventTime;

    @Column(name = "prev_hash")
    private String prevHash;

    @Column(name = "hash", nullable = false)
    private String hash;

    @Column(name = "occurred_at", nullable = false)
    private LocalDateTime occurredAt;
}

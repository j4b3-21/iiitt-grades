package example.example.grading_engine.model.entity;

import example.example.grading_engine.enums.auditlogging.AuditAction;
import example.example.grading_engine.enums.auditlogging.AuditEntityType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

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

    public AuditLog() {
    }

    public AuditLog(UUID id,
                    User actor,
                    AuditAction action,
                    AuditEntityType entityType,
                    UUID entityId,
                    String previousState,
                    String newState,
                    LocalDateTime eventTime,
                    String prevHash,
                    String hash,
                    LocalDateTime occurredAt) {
        this.id = id;
        this.actor = actor;
        this.action = action;
        this.entityType = entityType;
        this.entityId = entityId;
        this.previousState = previousState;
        this.newState = newState;
        this.eventTime = eventTime;
        this.prevHash = prevHash;
        this.hash = hash;
        this.occurredAt = occurredAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getActor() {
        return actor;
    }

    public void setActor(User actor) {
        this.actor = actor;
    }

    public AuditAction getAction() {
        return action;
    }

    public void setAction(AuditAction action) {
        this.action = action;
    }

    public AuditEntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(AuditEntityType entityType) {
        this.entityType = entityType;
    }

    public UUID getEntityId() {
        return entityId;
    }

    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }

    public String getPreviousState() {
        return previousState;
    }

    public void setPreviousState(String previousState) {
        this.previousState = previousState;
    }

    public String getNewState() {
        return newState;
    }

    public void setNewState(String newState) {
        this.newState = newState;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(LocalDateTime occurredAt) {
        this.occurredAt = occurredAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditLog auditLog = (AuditLog) o;
        return Objects.equals(id, auditLog.id) &&
               Objects.equals(actor, auditLog.actor) &&
               action == auditLog.action &&
               entityType == auditLog.entityType &&
               Objects.equals(entityId, auditLog.entityId) &&
               Objects.equals(previousState, auditLog.previousState) &&
               Objects.equals(newState, auditLog.newState) &&
               Objects.equals(eventTime, auditLog.eventTime) &&
               Objects.equals(prevHash, auditLog.prevHash) &&
               Objects.equals(hash, auditLog.hash) &&
               Objects.equals(occurredAt, auditLog.occurredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actor, action, entityType, entityId, previousState, newState, eventTime, prevHash, hash, occurredAt);
    }

    @Override
    public String toString() {
        return "AuditLog{" +
               "id=" + id +
               ", actor=" + actor +
               ", action=" + action +
               ", entityType=" + entityType +
               ", entityId=" + entityId +
               ", previousState='" + previousState + '\'' +
               ", newState='" + newState + '\'' +
               ", eventTime=" + eventTime +
               ", prevHash='" + prevHash + '\'' +
               ", hash='" + hash + '\'' +
               ", occurredAt=" + occurredAt +
               '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private User actor;
        private AuditAction action;
        private AuditEntityType entityType;
        private UUID entityId;
        private String previousState;
        private String newState;
        private LocalDateTime eventTime;
        private String prevHash;
        private String hash;
        private LocalDateTime occurredAt;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder actor(User actor) {
            this.actor = actor;
            return this;
        }

        public Builder action(AuditAction action) {
            this.action = action;
            return this;
        }

        public Builder entityType(AuditEntityType entityType) {
            this.entityType = entityType;
            return this;
        }

        public Builder entityId(UUID entityId) {
            this.entityId = entityId;
            return this;
        }

        public Builder previousState(String previousState) {
            this.previousState = previousState;
            return this;
        }

        public Builder newState(String newState) {
            this.newState = newState;
            return this;
        }

        public Builder eventTime(LocalDateTime eventTime) {
            this.eventTime = eventTime;
            return this;
        }

        public Builder prevHash(String prevHash) {
            this.prevHash = prevHash;
            return this;
        }

        public Builder hash(String hash) {
            this.hash = hash;
            return this;
        }

        public Builder occurredAt(LocalDateTime occurredAt) {
            this.occurredAt = occurredAt;
            return this;
        }

        public AuditLog build() {
            return new AuditLog(id, actor, action, entityType, entityId, previousState, newState, eventTime, prevHash, hash, occurredAt);
        }
    }
}
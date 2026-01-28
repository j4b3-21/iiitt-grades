package example.example.grading_engine.service.impl;

import example.example.grading_engine.enums.auditlogging.AuditAction;
import example.example.grading_engine.enums.auditlogging.AuditEntityType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuditLogService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void logLoginSuccess(UUID userId) {
        insertAudit(userId, AuditAction.LOGIN_SUCCESS, AuditEntityType.USER, userId);
    }

    @Transactional
    public void logLoginFailure(String email) {
        insertAudit(null, AuditAction.LOGIN_FAILURE, AuditEntityType.USER, null);
    }

    @Transactional
    private void insertAudit(
            UUID actorId,
            AuditAction action,
            AuditEntityType entityType,
            UUID entityId
    ) {
        entityManager.createNativeQuery("""
            INSERT INTO audit_log
            (id, actor_id, action, entity_type, entity_id, hash, occurred_at)
            VALUES (
                gen_random_uuid(),
                ?,
                CAST(? AS audit_action),
                CAST(? AS audit_entity_type),
                ?,
                ?,
                ?
            )
        """)
                .setParameter(1, actorId)
                .setParameter(2, action.name())
                .setParameter(3, entityType.name())
                .setParameter(4, entityId)
                .setParameter(5, UUID.randomUUID().toString())
                .setParameter(6, LocalDateTime.now())
                .executeUpdate();
    }
}

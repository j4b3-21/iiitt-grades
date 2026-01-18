package example.example.grading_engine.enums.auditlogging;

public enum AuditAction {
    LOGIN_SUCCESS(1),
    LOGIN_FAILURE(2),

    MARKS_SAVED(10),
    MARKS_SUBMITTED(11),

    GRADES_APPROVED(20),
    GRADES_REJECTED(21),
    GRADES_LOCKED(22),

    EXPORT_GENERATED(30),
    UNAUTHORIZED_ACCESS(99);

    private final int code;

    AuditAction(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}


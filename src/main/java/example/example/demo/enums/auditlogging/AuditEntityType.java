package example.example.demo.enums.auditlogging;
public enum AuditEntityType {
    USER(1),
    SUBJECT(2),
    MARKS(3),
    GRADES(4),
    EXPORT(5);

    private final int code;

    AuditEntityType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

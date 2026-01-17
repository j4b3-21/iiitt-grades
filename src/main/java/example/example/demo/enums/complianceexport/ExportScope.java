package example.example.demo.enums.complianceexport;

public enum ExportScope {
    SUBJECT(1),
    STUDENT(2),
    DEPARTMENT(3),
    ACADEMIC_YEAR(4);

    private final int code;

    ExportScope(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}


package example.example.grading_engine.enums.academicstructure;

public enum ApprovalDecision {
    APPROVE(1),
    REJECT(2);

    private final int code;

    ApprovalDecision(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}


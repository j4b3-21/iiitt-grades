package example.example.demo.enums.workflowapproval;

public enum SubmissionStatus {
    DRAFT(0),
    SUBMITTED(1),
    APPROVED(2),
    LOCKED(3);

    private final int code;

    SubmissionStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

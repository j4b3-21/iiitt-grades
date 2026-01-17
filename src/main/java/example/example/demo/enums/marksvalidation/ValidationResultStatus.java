package example.example.demo.enums.marksvalidation;

public enum ValidationResultStatus {
    VALID(1),
    INVALID(0);

    private final int code;

    ValidationResultStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}


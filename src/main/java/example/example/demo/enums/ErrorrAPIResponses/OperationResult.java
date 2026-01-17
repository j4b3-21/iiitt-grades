package example.example.demo.enums.ErrorrAPIResponses;

public enum OperationResult {
    SUCCESS(1),
    FAILURE(0);

    private final int code;

    OperationResult(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}


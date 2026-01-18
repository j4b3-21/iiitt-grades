package example.example.grading_engine.enums.ErrorrAPIResponses;

public enum ErrorCode {
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    RESOURCE_NOT_FOUND(404),
    VALIDATION_FAILED(422),
    INVALID_STATE(409),
    OPERATION_NOT_ALLOWED(400);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}


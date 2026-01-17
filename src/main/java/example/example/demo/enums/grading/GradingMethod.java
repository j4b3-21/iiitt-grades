package example.example.demo.enums.grading;

public enum GradingMethod {
    RELATIVE(1),
    ABSOLUTE(2);

    private final int code;

    GradingMethod(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}


package example.example.grading_engine.enums.marksvalidation;

public enum MarkComponentType {
    INTERNAL(1),
    EXTERNAL(2),
    LAB(3);

    private final int code;

    MarkComponentType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}


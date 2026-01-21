package example.example.grading_engine.enums.marksvalidation;

public enum MarkComponentType {
    CT1(1),
    CT2(2),
    CT3(3),
    ENDSEMESTER(4),
    MODELLAB(5),
    ENDSEMESTERLAB(6),
    INTERNAL(7),
    EXTERNAL(8),
    FINAL(9);

    private final int code;

    MarkComponentType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}


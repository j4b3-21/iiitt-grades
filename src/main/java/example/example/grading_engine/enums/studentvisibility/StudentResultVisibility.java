package example.example.grading_engine.enums.studentvisibility;

public enum StudentResultVisibility {
    NOT_PUBLISHED(0),
    PUBLISHED(1);

    private final int code;

    StudentResultVisibility(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}


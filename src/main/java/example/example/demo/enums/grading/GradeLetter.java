package example.example.demo.enums.grading;

public enum GradeLetter {
    A(10),
    B(9),
    C(8),
    D(7),
    E(6),
    F(0);

    private final int code;

    GradeLetter(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}


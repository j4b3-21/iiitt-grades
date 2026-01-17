package example.example.demo.enums.academicstructure;
public enum SemesterType {
    ODD(1),
    EVEN(2);

    private final int code;

    SemesterType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

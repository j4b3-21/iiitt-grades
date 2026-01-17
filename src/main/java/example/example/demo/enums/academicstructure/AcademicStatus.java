package example.example.demo.enums.academicstructure;
public enum AcademicStatus {
    ACTIVE(1),
    INACTIVE(0);

    private final int code;

    AcademicStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

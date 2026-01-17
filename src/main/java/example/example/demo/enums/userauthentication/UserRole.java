package example.example.demo.enums.userauthentication;
public enum UserRole {
    STUDENT(1),
    FACULTY(2),
    HOD(3),
    SYSTEM(9);

    private final int code;

    UserRole(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

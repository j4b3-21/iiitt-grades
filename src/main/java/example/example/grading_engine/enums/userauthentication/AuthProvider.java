package example.example.grading_engine.enums.userauthentication;
public enum AuthProvider {
    LOCAL(1),    // Faculty, HoD
    GOOGLE(2);   // Students

    private final int code;

    AuthProvider(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

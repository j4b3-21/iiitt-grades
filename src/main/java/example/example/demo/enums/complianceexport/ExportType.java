package example.example.demo.enums.complianceexport;

public enum ExportType {
    CSV(1),
    PDF(2);

    private final int code;

    ExportType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}


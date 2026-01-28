package example.example.grading_engine.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class StudentMarksPerSubjectDTO {
    private UUID studentId;
    private String rollNumber;
    private BigDecimal totalMarks;

    public StudentMarksPerSubjectDTO(
            UUID studentId,
            String rollNumber,
            BigDecimal totalMarks
    ) {
        this.studentId = studentId;
        this.rollNumber = rollNumber;
        this.totalMarks = totalMarks;
    }

    public UUID getStudentId() { return studentId; }
    public String getRollNumber() { return rollNumber; }
    public BigDecimal getTotalMarks() { return totalMarks; }

}

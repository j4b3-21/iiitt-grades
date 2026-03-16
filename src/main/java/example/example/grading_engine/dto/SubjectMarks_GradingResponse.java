
package example.example.grading_engine.dto;

import com.nimbusds.jose.util.Pair;
import example.example.grading_engine.enums.grading.GradeLetter;
import example.example.grading_engine.enums.marksvalidation.MarkComponentType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record SubjectMarks_GradingResponse(
        String classCode,
        String subjectCode,
        List<StudentInitialGrade> students,
        BigDecimal meanTotal,
        BigDecimal standardDeviationTotal,
        Map<GradeLetter, Pair<BigDecimal, BigDecimal>> gradeBoundaries) {
    public static class StudentInitialGrade {

        private final UUID studentId;
        private final UUID enrollmentId;
        private final String rollNumber;
        private final Map<MarkComponentType, BigDecimal> marksByType;
        private final BigDecimal total;
        private final BigDecimal average;
        private GradeLetter grade;
        Map<GradeLetter, Pair<BigDecimal, BigDecimal>> gradeBoundaries;

        public StudentInitialGrade(
                UUID studentId,
                UUID enrollmentId,
                String rollNumber,
                Map<MarkComponentType, BigDecimal> marksByType,
                BigDecimal total,
                BigDecimal average
        ) {
            this.studentId = studentId;
            this.enrollmentId = enrollmentId;
            this.rollNumber = rollNumber;
            this.marksByType = marksByType;
            this.total = total;
            this.average = average;
        }

        public UUID getEnrollmentId() {return enrollmentId;}
        public UUID getStudentId() { return studentId; }
        public String getRollNumber() { return rollNumber; }
        public Map<MarkComponentType, BigDecimal> getMarksByType() { return marksByType; }
        public BigDecimal getTotal() { return total; }
        public BigDecimal getAverage() { return average; }
        public GradeLetter getGrade() { return grade; }

        public Map<GradeLetter, Pair<BigDecimal, BigDecimal>> getGradeBoundaries() {
            return gradeBoundaries;
        }

        public void setGradeBoundaries(Map<GradeLetter, Pair<BigDecimal, BigDecimal>> gradeBoundaries) {
            this.gradeBoundaries = gradeBoundaries;
        }

        public void setGrade(GradeLetter grade) {
            this.grade = grade;
        }

    }
}

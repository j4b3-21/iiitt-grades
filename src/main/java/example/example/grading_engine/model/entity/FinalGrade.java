package example.example.grading_engine.model.entity;


import example.example.grading_engine.enums.grading.GradeLetter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "final_grades", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "subject_id", "academic_year"})
})
public class FinalGrade {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade", nullable = false)
    private GradeLetter grade;

    @Column(name = "total_marks", nullable = false)
    private Integer totalMarks;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public FinalGrade() {
    }

    public FinalGrade(
            UUID id,
            Student student,
            Subject subject,
            String academicYear,
            GradeLetter grade,
            Integer totalMarks,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.student = student;
        this.subject = subject;
        this.academicYear = academicYear;
        this.grade = grade;
        this.totalMarks = totalMarks;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public GradeLetter getGrade() {
        return grade;
    }

    public void setGrade(GradeLetter grade) {
        this.grade = grade;
    }

    public Integer getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Integer totalMarks) {
        this.totalMarks = totalMarks;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalGrade that = (FinalGrade) o;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FinalGrade{" +
                "id=" + id +
                ", academicYear='" + academicYear + '\'' +
                ", grade=" + grade +
                ", totalMarks=" + totalMarks +
                ", createdAt=" + createdAt +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private Student student;
        private Subject subject;
        private String academicYear;
        private GradeLetter grade;
        private Integer totalMarks;
        private LocalDateTime createdAt;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder student(Student student) {
            this.student = student;
            return this;
        }

        public Builder subject(Subject subject) {
            this.subject = subject;
            return this;
        }

        public Builder academicYear(String academicYear) {
            this.academicYear = academicYear;
            return this;
        }

        public Builder grade(GradeLetter grade) {
            this.grade = grade;
            return this;
        }

        public Builder totalMarks(Integer totalMarks) {
            this.totalMarks = totalMarks;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public FinalGrade build() {
            return new FinalGrade(
                    id,
                    student,
                    subject,
                    academicYear,
                    grade,
                    totalMarks,
                    createdAt
            );
        }
    }
}

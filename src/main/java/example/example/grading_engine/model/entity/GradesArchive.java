package example.example.grading_engine.model.entity;


import example.example.grading_engine.enums.grading.GradeLetter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "grades_archive")
public class GradesArchive {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id")
    private StudentEnrollment enrollment;

    @Column(name = "semester", nullable = false)
    private Integer semester;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade_letter", nullable = false)
    private GradeLetter gradeLetter;

    @Column(name = "archived_at", nullable = false)
    private LocalDateTime archivedAt;

    @Column(name = "policy_version")
    private String policyVersion;

    public GradesArchive() {
    }

    public GradesArchive(
            UUID id,
            StudentEnrollment enrollment,
            Integer semester,
            GradeLetter gradeLetter,
            LocalDateTime archivedAt,
            String policyVersion
    ) {
        this.id = id;
        this.enrollment = enrollment;
        this.semester = semester;
        this.gradeLetter = gradeLetter;
        this.archivedAt = archivedAt;
        this.policyVersion = policyVersion;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StudentEnrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(StudentEnrollment enrollment) {
        this.enrollment = enrollment;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public GradeLetter getGradeLetter() {
        return gradeLetter;
    }

    public void setGradeLetter(GradeLetter gradeLetter) {
        this.gradeLetter = gradeLetter;
    }

    public LocalDateTime getArchivedAt() {
        return archivedAt;
    }

    public void setArchivedAt(LocalDateTime archivedAt) {
        this.archivedAt = archivedAt;
    }

    public String getPolicyVersion() {
        return policyVersion;
    }

    public void setPolicyVersion(String policyVersion) {
        this.policyVersion = policyVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradesArchive that = (GradesArchive) o;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GradesArchive{" +
                "id=" + id +
                ", semester=" + semester +
                ", gradeLetter=" + gradeLetter +
                ", archivedAt=" + archivedAt +
                ", policyVersion='" + policyVersion + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private StudentEnrollment enrollment;
        private Integer semester;
        private GradeLetter gradeLetter;
        private LocalDateTime archivedAt;
        private String policyVersion;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder enrollment(StudentEnrollment enrollment) {
            this.enrollment = enrollment;
            return this;
        }

        public Builder semester(Integer semester) {
            this.semester = semester;
            return this;
        }

        public Builder gradeLetter(GradeLetter gradeLetter) {
            this.gradeLetter = gradeLetter;
            return this;
        }

        public Builder archivedAt(LocalDateTime archivedAt) {
            this.archivedAt = archivedAt;
            return this;
        }

        public Builder policyVersion(String policyVersion) {
            this.policyVersion = policyVersion;
            return this;
        }

        public GradesArchive build() {
            return new GradesArchive(
                    id,
                    enrollment,
                    semester,
                    gradeLetter,
                    archivedAt,
                    policyVersion
            );
        }
    }
}

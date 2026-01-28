package example.example.grading_engine.model.entity;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "academic_session", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"academic_year", "semester"})
})
public class AcademicSession {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Column(name = "semester", nullable = false)
    private Integer semester;

    @Column(name = "regulation_version", nullable = false)
    private String regulationVersion;

    @Column(name = "grading_policy_ver", nullable = false)
    private String gradingPolicyVer;

    public AcademicSession() {
    }

    public AcademicSession(UUID id, String academicYear, Integer semester, String regulationVersion, String gradingPolicyVer) {
        this.id = id;
        this.academicYear = academicYear;
        this.semester = semester;
        this.regulationVersion = regulationVersion;
        this.gradingPolicyVer = gradingPolicyVer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getRegulationVersion() {
        return regulationVersion;
    }

    public void setRegulationVersion(String regulationVersion) {
        this.regulationVersion = regulationVersion;
    }

    public String getGradingPolicyVer() {
        return gradingPolicyVer;
    }

    public void setGradingPolicyVer(String gradingPolicyVer) {
        this.gradingPolicyVer = gradingPolicyVer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AcademicSession that = (AcademicSession) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(academicYear, that.academicYear) &&
                Objects.equals(semester, that.semester) &&
                Objects.equals(regulationVersion, that.regulationVersion) &&
                Objects.equals(gradingPolicyVer, that.gradingPolicyVer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, academicYear, semester, regulationVersion, gradingPolicyVer);
    }

    @Override
    public String toString() {
        return "AcademicSession{" +
                "id=" + id +
                ", academicYear='" + academicYear + '\'' +
                ", semester=" + semester +
                ", regulationVersion='" + regulationVersion + '\'' +
                ", gradingPolicyVer='" + gradingPolicyVer + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private String academicYear;
        private Integer semester;
        private String regulationVersion;
        private String gradingPolicyVer;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder academicYear(String academicYear) {
            this.academicYear = academicYear;
            return this;
        }

        public Builder semester(Integer semester) {
            this.semester = semester;
            return this;
        }

        public Builder regulationVersion(String regulationVersion) {
            this.regulationVersion = regulationVersion;
            return this;
        }

        public Builder gradingPolicyVer(String gradingPolicyVer) {
            this.gradingPolicyVer = gradingPolicyVer;
            return this;
        }

        public AcademicSession build() {
            return new AcademicSession(id, academicYear, semester, regulationVersion, gradingPolicyVer);
        }
    }
}

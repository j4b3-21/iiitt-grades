
package example.example.grading_engine.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class FacultySubjectId implements Serializable {

    @Column(name = "faculty_id", columnDefinition = "uuid")
    private UUID facultyId;

    @Column(name = "subject_id", columnDefinition = "uuid")
    private UUID subjectId;

    @Column(name = "academic_year")
    private String academicYear;

    public FacultySubjectId() {
    }

    public FacultySubjectId(UUID facultyId, UUID subjectId, String academicYear) {
        this.facultyId = facultyId;
        this.subjectId = subjectId;
        this.academicYear = academicYear;
    }

    public UUID getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(UUID facultyId) {
        this.facultyId = facultyId;
    }

    public UUID getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(UUID subjectId) {
        this.subjectId = subjectId;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultySubjectId that = (FacultySubjectId) o;
        if (facultyId != null ? !facultyId.equals(that.facultyId) : that.facultyId != null) return false;
        if (subjectId != null ? !subjectId.equals(that.subjectId) : that.subjectId != null) return false;
        return academicYear != null ? academicYear.equals(that.academicYear) : that.academicYear == null;
    }

    @Override
    public int hashCode() {
        int result = facultyId != null ? facultyId.hashCode() : 0;
        result = 31 * result + (subjectId != null ? subjectId.hashCode() : 0);
        result = 31 * result + (academicYear != null ? academicYear.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FacultySubjectId{" +
                "facultyId=" + facultyId +
                ", subjectId=" + subjectId +
                ", academicYear='" + academicYear + '\'' +
                '}';
    }
}
package example.example.grading_engine.model.entity;


import example.example.grading_engine.enums.academicstructure.SemesterType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "faculty_subjects")
public class FacultySubject {

    @EmbeddedId
    private FacultySubjectId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("facultyId")
    @JoinColumn(name = "faculty_id", nullable = false)
    private User faculty;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "assigned_at", nullable = false)
    private LocalDateTime assignedAt;

    @Column(name = "semester", nullable = false)
    private Integer semester;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester_type", nullable = false)
    private SemesterType semesterType;

    public FacultySubject() {
    }

    public FacultySubject(
            FacultySubjectId id,
            User faculty,
            Subject subject,
            LocalDateTime assignedAt,
            Integer semester,
            SemesterType semesterType
    ) {
        this.id = id;
        this.faculty = faculty;
        this.subject = subject;
        this.assignedAt = assignedAt;
        this.semester = semester;
        this.semesterType = semesterType;
    }

    public FacultySubjectId getId() {
        return id;
    }

    public void setId(FacultySubjectId id) {
        this.id = id;
    }

    public User getFaculty() {
        return faculty;
    }

    public void setFaculty(User faculty) {
        this.faculty = faculty;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public SemesterType getSemesterType() {
        return semesterType;
    }

    public void setSemesterType(SemesterType semesterType) {
        this.semesterType = semesterType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultySubject that = (FacultySubject) o;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FacultySubject{" +
                "id=" + id +
                ", semester=" + semester +
                ", semesterType=" + semesterType +
                ", assignedAt=" + assignedAt +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private FacultySubjectId id;
        private User faculty;
        private Subject subject;
        private LocalDateTime assignedAt;
        private Integer semester;
        private SemesterType semesterType;

        public Builder id(FacultySubjectId id) {
            this.id = id;
            return this;
        }

        public Builder faculty(User faculty) {
            this.faculty = faculty;
            return this;
        }

        public Builder subject(Subject subject) {
            this.subject = subject;
            return this;
        }

        public Builder assignedAt(LocalDateTime assignedAt) {
            this.assignedAt = assignedAt;
            return this;
        }

        public Builder semester(Integer semester) {
            this.semester = semester;
            return this;
        }

        public Builder semesterType(SemesterType semesterType) {
            this.semesterType = semesterType;
            return this;
        }

        public FacultySubject build() {
            return new FacultySubject(
                    id,
                    faculty,
                    subject,
                    assignedAt,
                    semester,
                    semesterType
            );
        }
    }
}

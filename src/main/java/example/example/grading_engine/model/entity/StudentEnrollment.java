package example.example.grading_engine.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "student_enrollments", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "subject_id", "session_id"})
})
public class StudentEnrollment {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private AcademicSession session;

    public StudentEnrollment() {
    }

    public StudentEnrollment(
            UUID id,
            Student student,
            Subject subject,
            AcademicSession session
    ) {
        this.id = id;
        this.student = student;
        this.subject = subject;
        this.session = session;
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

    public AcademicSession getSession() {
        return session;
    }

    public void setSession(AcademicSession session) {
        this.session = session;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEnrollment that = (StudentEnrollment) o;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "StudentEnrollment{" +
                "id=" + id +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private Student student;
        private Subject subject;
        private AcademicSession session;

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

        public Builder session(AcademicSession session) {
            this.session = session;
            return this;
        }

        public StudentEnrollment build() {
            return new StudentEnrollment(
                    id,
                    student,
                    subject,
                    session
            );
        }
    }
}

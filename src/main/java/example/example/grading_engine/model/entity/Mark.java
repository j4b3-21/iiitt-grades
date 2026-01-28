package example.example.grading_engine.model.entity;


import example.example.grading_engine.enums.marksvalidation.MarkComponentType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "marks", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"enrollment_id", "marks_type"})
})
public class Mark {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private StudentEnrollment enrollment;

    @Enumerated(EnumType.STRING)
    @Column(name = "marks_type", nullable = false)
    private MarkComponentType marksType;

    @Column(name = "marks", nullable = false, precision = 5, scale = 2)
    private BigDecimal marks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entered_by", nullable = false)
    private User enteredBy;

    @Column(name = "entered_at", nullable = false)
    private LocalDateTime enteredAt;

    public Mark() {
    }

    public Mark(
            UUID id,
            StudentEnrollment enrollment,
            MarkComponentType marksType,
            BigDecimal marks,
            User enteredBy,
            LocalDateTime enteredAt
    ) {
        this.id = id;
        this.enrollment = enrollment;
        this.marksType = marksType;
        this.marks = marks;
        this.enteredBy = enteredBy;
        this.enteredAt = enteredAt;
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

    public MarkComponentType getMarksType() {
        return marksType;
    }

    public void setMarksType(MarkComponentType marksType) {
        this.marksType = marksType;
    }

    public BigDecimal getMarks() {
        return marks;
    }

    public void setMarks(BigDecimal marks) {
        this.marks = marks;
    }

    public User getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(User enteredBy) {
        this.enteredBy = enteredBy;
    }

    public LocalDateTime getEnteredAt() {
        return enteredAt;
    }

    public void setEnteredAt(LocalDateTime enteredAt) {
        this.enteredAt = enteredAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return id != null ? id.equals(mark.id) : mark.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", marksType=" + marksType +
                ", marks=" + marks +
                ", enteredAt=" + enteredAt +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private StudentEnrollment enrollment;
        private MarkComponentType marksType;
        private BigDecimal marks;
        private User enteredBy;
        private LocalDateTime enteredAt;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder enrollment(StudentEnrollment enrollment) {
            this.enrollment = enrollment;
            return this;
        }

        public Builder marksType(MarkComponentType marksType) {
            this.marksType = marksType;
            return this;
        }

        public Builder marks(BigDecimal marks) {
            this.marks = marks;
            return this;
        }

        public Builder enteredBy(User enteredBy) {
            this.enteredBy = enteredBy;
            return this;
        }

        public Builder enteredAt(LocalDateTime enteredAt) {
            this.enteredAt = enteredAt;
            return this;
        }

        public Mark build() {
            return new Mark(
                    id,
                    enrollment,
                    marksType,
                    marks,
                    enteredBy,
                    enteredAt
            );
        }
    }
}

package example.example.grading_engine.model.entity;

import example.example.grading_engine.enums.grading.GradeLetter;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "grade_boundaries")
public class GradeBoundary {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id", nullable = false)
    private GradeSubmission submission;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade_letter", nullable = false)
    private GradeLetter gradeLetter;

    @Column(name = "min_score", nullable = false, precision = 5, scale = 2)
    private BigDecimal minScore;

    @Column(name = "max_score", nullable = false, precision = 5, scale = 2)
    private BigDecimal maxScore;

    public GradeBoundary() {
    }

    public GradeBoundary(
            UUID id,
            GradeSubmission submission,
            GradeLetter gradeLetter,
            BigDecimal minScore,
            BigDecimal maxScore
    ) {
        this.id = id;
        this.submission = submission;
        this.gradeLetter = gradeLetter;
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public GradeSubmission getSubmission() {
        return submission;
    }

    public void setSubmission(GradeSubmission submission) {
        this.submission = submission;
    }

    public GradeLetter getGradeLetter() {
        return gradeLetter;
    }

    public void setGradeLetter(GradeLetter gradeLetter) {
        this.gradeLetter = gradeLetter;
    }

    public BigDecimal getMinScore() {
        return minScore;
    }

    public void setMinScore(BigDecimal minScore) {
        this.minScore = minScore;
    }

    public BigDecimal getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(BigDecimal maxScore) {
        this.maxScore = maxScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeBoundary that = (GradeBoundary) o;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GradeBoundary{" +
                "id=" + id +
                ", gradeLetter=" + gradeLetter +
                ", minScore=" + minScore +
                ", maxScore=" + maxScore +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private GradeSubmission submission;
        private GradeLetter gradeLetter;
        private BigDecimal minScore;
        private BigDecimal maxScore;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder submission(GradeSubmission submission) {
            this.submission = submission;
            return this;
        }

        public Builder gradeLetter(GradeLetter gradeLetter) {
            this.gradeLetter = gradeLetter;
            return this;
        }

        public Builder minScore(BigDecimal minScore) {
            this.minScore = minScore;
            return this;
        }

        public Builder maxScore(BigDecimal maxScore) {
            this.maxScore = maxScore;
            return this;
        }

        public GradeBoundary build() {
            return new GradeBoundary(
                    id,
                    submission,
                    gradeLetter,
                    minScore,
                    maxScore
            );
        }
    }
}

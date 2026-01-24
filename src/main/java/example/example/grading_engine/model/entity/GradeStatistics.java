package example.example.grading_engine.model.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "grade_statistics")
public class GradeStatistics {

    @Id
    @Column(name = "submission_id", columnDefinition = "uuid")
    private UUID submissionId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "submission_id")
    private GradeSubmission submission;

    @Column(name = "mean", nullable = false, precision = 10, scale = 4)
    private BigDecimal mean;

    @Column(name = "std_deviation", nullable = false, precision = 10, scale = 4)
    private BigDecimal stdDeviation;

    public GradeStatistics() {
    }

    public GradeStatistics(
            UUID submissionId,
            GradeSubmission submission,
            BigDecimal mean,
            BigDecimal stdDeviation
    ) {
        this.submissionId = submissionId;
        this.submission = submission;
        this.mean = mean;
        this.stdDeviation = stdDeviation;
    }

    public UUID getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(UUID submissionId) {
        this.submissionId = submissionId;
    }

    public GradeSubmission getSubmission() {
        return submission;
    }

    public void setSubmission(GradeSubmission submission) {
        this.submission = submission;
    }

    public BigDecimal getMean() {
        return mean;
    }

    public void setMean(BigDecimal mean) {
        this.mean = mean;
    }

    public BigDecimal getStdDeviation() {
        return stdDeviation;
    }

    public void setStdDeviation(BigDecimal stdDeviation) {
        this.stdDeviation = stdDeviation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeStatistics that = (GradeStatistics) o;
        return submissionId != null ? submissionId.equals(that.submissionId) : that.submissionId == null;
    }

    @Override
    public int hashCode() {
        return submissionId != null ? submissionId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GradeStatistics{" +
                "submissionId=" + submissionId +
                ", mean=" + mean +
                ", stdDeviation=" + stdDeviation +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID submissionId;
        private GradeSubmission submission;
        private BigDecimal mean;
        private BigDecimal stdDeviation;

        public Builder submissionId(UUID submissionId) {
            this.submissionId = submissionId;
            return this;
        }

        public Builder submission(GradeSubmission submission) {
            this.submission = submission;
            return this;
        }

        public Builder mean(BigDecimal mean) {
            this.mean = mean;
            return this;
        }

        public Builder stdDeviation(BigDecimal stdDeviation) {
            this.stdDeviation = stdDeviation;
            return this;
        }

        public GradeStatistics build() {
            return new GradeStatistics(
                    submissionId,
                    submission,
                    mean,
                    stdDeviation
            );
        }
    }
}

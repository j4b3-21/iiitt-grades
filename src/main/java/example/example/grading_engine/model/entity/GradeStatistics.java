package example.example.grading_engine.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}

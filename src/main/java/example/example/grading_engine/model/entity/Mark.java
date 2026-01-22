package example.example.grading_engine.model.entity;


import example.example.grading_engine.enums.marksvalidation.MarkComponentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}

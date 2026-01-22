package example.example.grading_engine.model.entity;


import example.example.grading_engine.enums.grading.GradeLetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "final_grades", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "subject_id", "academic_year"})
})
public class FinalGrade {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade", nullable = false)
    private GradeLetter grade;

    @Column(name = "total_marks", nullable = false)
    private Integer totalMarks;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}

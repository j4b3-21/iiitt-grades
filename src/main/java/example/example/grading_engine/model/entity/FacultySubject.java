package example.example.grading_engine.model.entity;


import example.example.grading_engine.enums.academicstructure.SemesterType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}

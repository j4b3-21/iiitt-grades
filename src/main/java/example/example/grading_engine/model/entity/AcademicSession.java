package example.example.grading_engine.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "academic_session", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"academic_year", "semester"})
})
public class AcademicSession {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Column(name = "semester", nullable = false)
    private Integer semester;

    @Column(name = "regulation_version", nullable = false)
    private String regulationVersion;

    @Column(name = "grading_policy_ver", nullable = false)
    private String gradingPolicyVer;
}

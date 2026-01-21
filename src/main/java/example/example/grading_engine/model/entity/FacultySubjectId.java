
package example.example.grading_engine.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class FacultySubjectId implements Serializable {
    @Column(name = "faculty_id", columnDefinition = "uuid")
    private UUID facultyId;

    @Column(name = "subject_id", columnDefinition = "uuid")
    private UUID subjectId;

    @Column(name = "academic_year")
    private String academicYear;
}
package example.example.grading_engine.repository;

import example.example.grading_engine.model.entity.FinalGrade;
import example.example.grading_engine.model.entity.GradeSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FinalGradeRepository extends JpaRepository<FinalGrade, UUID> {

    @Query("""
        select fg.submission
        from FinalGrade fg
        join fg.enrollment e
        join e.subject s
        join FacultySubject fs on fs.subject = s
        where fs.faculty.id = :facultyId
    """)
    List<GradeSubmission> findFinalGradesByFaculty(
            @Param("facultyId") UUID facultyId
    );
}
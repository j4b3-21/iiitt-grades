package example.example.grading_engine.repository;

import example.example.grading_engine.dto.StudentMarksPerSubjectDTO;
import example.example.grading_engine.model.entity.Mark;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentMarksRepository
        extends Repository<Mark, Long> {

    @Query(
            value = """
            SELECT
                s.id AS studentId,
                s.roll_number AS rollNumber,
                tm.total_marks AS totalMarks
            FROM total_marks_per_enrollment tm
            JOIN student_enrollments se
                 ON se.id = tm.enrollment_id
            JOIN students s
                 ON s.id = tm.student_id
            JOIN subjects sub
                 ON sub.id = se.subject_id
            JOIN academic_session a
                 ON a.id = se.session_id
            WHERE sub.subject_code = :subjectCode
              AND a.academic_year = :academicYear
              AND a.semester = :semester
              
            """,
            nativeQuery = true
    )
    List<StudentMarksPerSubjectDTO> fetchTotalMarksForSubject(
            @Param("subjectCode") String subjectCode,
            @Param("academicYear") String academicYear,
            @Param("semester") Integer semester
    );
}

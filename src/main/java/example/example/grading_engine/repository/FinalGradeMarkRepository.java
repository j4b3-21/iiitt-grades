package example.example.grading_engine.repository;

import example.example.grading_engine.model.entity.FinalGradeMarks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FinalGradeMarkRepository extends JpaRepository<FinalGradeMarks, UUID> {
}

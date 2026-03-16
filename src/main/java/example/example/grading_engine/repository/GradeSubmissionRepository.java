package example.example.grading_engine.repository;

import example.example.grading_engine.model.entity.GradeSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GradeSubmissionRepository
        extends JpaRepository<GradeSubmission, UUID> {



}
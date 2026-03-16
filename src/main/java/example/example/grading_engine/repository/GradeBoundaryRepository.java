package example.example.grading_engine.repository;

import example.example.grading_engine.model.entity.GradeBoundary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GradeBoundaryRepository extends JpaRepository<GradeBoundary, UUID> {
}

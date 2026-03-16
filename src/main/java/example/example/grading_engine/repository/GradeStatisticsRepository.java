package example.example.grading_engine.repository;

import example.example.grading_engine.model.entity.GradeStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GradeStatisticsRepository extends JpaRepository<GradeStatistics, UUID> {
}

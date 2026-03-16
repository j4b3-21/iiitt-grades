package example.example.grading_engine.repository;

import example.example.grading_engine.model.entity.FacultySubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FacultySubjectRepository extends JpaRepository<FacultySubject, UUID> {
    List<FacultySubject> findByFaculty_IdAndSubject_IsActiveTrue(UUID facultyId);
}

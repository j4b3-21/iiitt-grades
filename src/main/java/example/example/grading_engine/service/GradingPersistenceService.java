package example.example.grading_engine.service;

import example.example.grading_engine.dto.SubjectMarks_GradingResponse;
import example.example.grading_engine.model.entity.GradeSubmission;

public interface GradingPersistenceService {

    GradeSubmission saveDraftGradingSnapshot(
            SubjectMarks_GradingResponse response,
            String policyVersion
    );

}
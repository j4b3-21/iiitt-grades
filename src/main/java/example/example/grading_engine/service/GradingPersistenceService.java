package example.example.grading_engine.service;

import example.example.grading_engine.dto.SubjectMarks_GradingResponse;

public interface GradingPersistenceService {

    void saveDraftGradingSnapshot(
            SubjectMarks_GradingResponse response,
            String policyVersion
    );

}
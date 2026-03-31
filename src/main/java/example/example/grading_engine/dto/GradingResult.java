package example.example.grading_engine.dto;

import example.example.grading_engine.model.entity.GradeSubmission;

public record GradingResult(
        SubjectMarks_GradingResponse response,
        GradeSubmission submission
) {}
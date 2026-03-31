package example.example.grading_engine.service.impl;

import com.nimbusds.jose.util.Pair;
import example.example.grading_engine.dto.SubjectMarks_GradingResponse;
import example.example.grading_engine.enums.grading.GradeLetter;
import example.example.grading_engine.enums.marksvalidation.MarkComponentType;
import example.example.grading_engine.enums.workflowapproval.SubmissionStatus;
import example.example.grading_engine.model.entity.*;
import example.example.grading_engine.repository.*;
import example.example.grading_engine.service.GradingPersistenceService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class GradingPersistenceServiceImpl
        implements GradingPersistenceService {

    private final GradeSubmissionRepository submissionRepo;
    private final GradeStatisticsRepository statisticsRepo;
    private final GradeBoundaryRepository boundaryRepo;
    private final FinalGradeRepository finalGradeRepo;
    private final FinalGradeMarkRepository finalGradeMarkRepo;
    private final jakarta.persistence.EntityManager entityManager;

    public GradingPersistenceServiceImpl(GradeSubmissionRepository submissionRepo, GradeStatisticsRepository statisticsRepo, GradeBoundaryRepository boundaryRepo, FinalGradeRepository finalGradeRepo, FinalGradeMarkRepository finalGradeMarkRepo, jakarta.persistence.EntityManager entityManager) {
        this.submissionRepo = submissionRepo;
        this.statisticsRepo = statisticsRepo;
        this.boundaryRepo = boundaryRepo;
        this.finalGradeRepo = finalGradeRepo;
        this.finalGradeMarkRepo = finalGradeMarkRepo;
        this.entityManager = entityManager;
    }

    @Override
    public GradeSubmission saveDraftGradingSnapshot(
            SubjectMarks_GradingResponse response,
            String policyVersion
    ) {
        GradeSubmission submission = createSubmission(policyVersion);
        GradeStatistics statistics = createStatistics(response);
        createBoundaries(statistics, response.gradeBoundaries());
        createFinalGrades(submission, statistics, response.students());
        return submission;
    }

    private GradeSubmission createSubmission(String policyVersion) {
        GradeSubmission submission = new GradeSubmission();
        submission.setStatus(SubmissionStatus.DRAFT);
        submission.setPolicyVersion(policyVersion);
        submission.setSubmittedAt(LocalDateTime.now());
        submission.setLockedAt(null);

        return submissionRepo.save(submission);
    }

    private GradeStatistics createStatistics(
            SubjectMarks_GradingResponse response
    ) {
        GradeStatistics stats = new GradeStatistics();
        stats.setMean(response.meanTotal());
        stats.setStdDeviation(response.standardDeviationTotal());

        return statisticsRepo.save(stats);
    }

    private void createFinalGrades(
            GradeSubmission submission,
            GradeStatistics statistics,
            List<SubjectMarks_GradingResponse.StudentInitialGrade> students
    ) {
        for (SubjectMarks_GradingResponse.StudentInitialGrade student : students) {

            FinalGrade finalGrade = new FinalGrade();
            StudentEnrollment enrollmentRef =
                    entityManager.getReference(
                            StudentEnrollment.class,
                            student.getEnrollmentId()
                    );

            finalGrade.setEnrollment(enrollmentRef);
            finalGrade.setSubmission(submission);
            finalGrade.setGradeLetter(student.getGrade());

            finalGrade = finalGradeRepo.save(finalGrade);

            createFinalGradeMarks(finalGrade, student.getMarksByType());
        }
    }
    private void createBoundaries(
            GradeStatistics statistics,
            Map<GradeLetter, Pair<BigDecimal, BigDecimal>> boundaries
    ) {
        for (Map.Entry<GradeLetter, Pair<BigDecimal, BigDecimal>> entry : boundaries.entrySet()) {

            GradeBoundary boundary = new GradeBoundary();
            boundary.setStatisticsId(statistics);
            boundary.setGradeLetter(entry.getKey());
            boundary.setMinScore(entry.getValue().getLeft());
            boundary.setMaxScore(entry.getValue().getRight());

            boundaryRepo.save(boundary);
        }
    }
    private void createFinalGradeMarks(
            FinalGrade finalGrade,
            Map<MarkComponentType, BigDecimal> marks
    ) {
        for (Map.Entry<MarkComponentType, BigDecimal> entry : marks.entrySet()) {

            FinalGradeMarks mark = new FinalGradeMarks();
            mark.setFinalGrade(finalGrade);
            mark.setMarkType(entry.getKey());
            mark.setMarks(entry.getValue());

            finalGradeMarkRepo.save(mark);
        }
    }
}
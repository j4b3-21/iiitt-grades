package example.example.grading_engine.service.impl;

import example.example.grading_engine.dto.SubjectMarks_GradingRequest;
import example.example.grading_engine.dto.SubjectMarks_GradingResponse;
import example.example.grading_engine.enums.marksvalidation.MarkComponentType;
import example.example.grading_engine.model.entity.Mark;
import example.example.grading_engine.policy.GradingPolicy;
import example.example.grading_engine.policy.PolicyContext;
import example.example.grading_engine.policy.PolicyRegistry;
import example.example.grading_engine.repository.MarksRepository;
import example.example.grading_engine.util.MarksCalculationUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class MarksService {

    private final MarksRepository marksRepository;
    private final PolicyRegistry policyRegistry;
    private final GradingPersistenceServiceImpl gradingPersistenceService;

    public MarksService(
            MarksRepository marksRepository,
            PolicyRegistry policyRegistry,
            GradingPersistenceServiceImpl gradingPersistenceService
    ) {
        this.marksRepository = marksRepository;
        this.policyRegistry = policyRegistry;
        this.gradingPersistenceService = gradingPersistenceService;
    }

    public SubjectMarks_GradingResponse getGrading(@Valid SubjectMarks_GradingRequest request) {


        List<MarkComponentType> types = request.markTypes();

        if (types == null || types.isEmpty()) {
            types = Arrays.asList(MarkComponentType.values());
        }

        List<Mark> marks = marksRepository.findByEnrollment_Student_ClassCodeAndEnrollment_Subject_SubjectCodeAndMarksTypeIn(
                request.classCode(),
                request.subjectCode(),
                types
        );

        Map<UUID, StudentBucket> byStudent = new LinkedHashMap<>();
        for (Mark m : marks) {
            if (m == null || m.getEnrollment() == null || m.getEnrollment().getStudent() == null) continue;
            UUID studentId = m.getEnrollment().getStudent().getId();
            UUID enrollmentId = m.getEnrollment().getId();
            String roll = m.getEnrollment().getStudent().getRollNumber();
            String policy = m.getEnrollment().getSession().getGradingPolicyVer();


            StudentBucket bucket = byStudent.computeIfAbsent(studentId, id -> new StudentBucket(id,enrollmentId, roll,policy));
            bucket.marks.put(m.getMarksType(), m.getMarks());
        }

        List<SubjectMarks_GradingResponse.StudentInitialGrade> entries = new ArrayList<>();
        List<BigDecimal> totals = new ArrayList<>();
        for (StudentBucket sb : byStudent.values()) {
            EnumMap<MarkComponentType, BigDecimal> complete =
                    MarksCalculationUtils.buildCompleteMarks(sb.marks, types);
            BigDecimal total = MarksCalculationUtils.calculateTotal(complete);
            BigDecimal average = MarksCalculationUtils.calculateAverage(total, complete.size());

            totals.add(total);

            entries.add(new SubjectMarks_GradingResponse.StudentInitialGrade(
                    sb.studentId,
                    sb.enrollmentId,
                    sb.rollNumber,
                    Map.copyOf(complete),
                    total,
                    average
            ));
        }

        BigDecimal mean = MarksCalculationUtils.calculateMean(totals);
        BigDecimal stddev = MarksCalculationUtils.calculateStdDev(totals, mean);


        // Resolve grading policy (assumes same policy for the entire class & subject)
        String policyVersion = marks.isEmpty()
                ? null
                : marks.getFirst().getEnrollment().getSession().getGradingPolicyVer();

        if (policyVersion == null) {
            throw new IllegalStateException("No grading policy version found for this request");
        }

        GradingPolicy policy = policyRegistry.get(policyVersion);

        PolicyContext context = new PolicyContext(
                request.classCode(),
                request.subjectCode(),
                entries,
                mean,
                stddev
        );

        SubjectMarks_GradingResponse response = policy.apply(context);
        gradingPersistenceService.saveDraftGradingSnapshot(
                response,
                policyVersion
        );
        return response;

    }

    // simple bucket class used while building the response
    private static class StudentBucket {
        final UUID studentId;
        final UUID enrollmentId;
        final String rollNumber;
        final EnumMap<MarkComponentType, BigDecimal> marks = new EnumMap<>(MarkComponentType.class);
        final String policy;

        StudentBucket(UUID studentId, UUID enrollmentId, String rollNumber, String policy) {
            this.studentId = studentId;
            this.enrollmentId = enrollmentId;
            this.rollNumber = rollNumber;
            this.policy = policy;
        }
    }

}

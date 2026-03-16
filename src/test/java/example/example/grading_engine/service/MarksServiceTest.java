package example.example.grading_engine.service;


import example.example.grading_engine.enums.grading.GradeLetter;
import example.example.grading_engine.dto.SubjectMarks_GradingRequest;
import example.example.grading_engine.dto.SubjectMarks_GradingResponse;
import example.example.grading_engine.enums.marksvalidation.MarkComponentType;
import example.example.grading_engine.model.entity.*;
import example.example.grading_engine.policy.PolicyRegistry;
import example.example.grading_engine.policy.impl.PolicyV1_StdDevRelative;
import example.example.grading_engine.repository.MarksRepository;
import example.example.grading_engine.service.impl.MarksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MarksServiceTest {

    private MarksRepository marksRepository;
    private MarksService marksService;

    @BeforeEach
    void setup() {
        marksRepository = Mockito.mock(MarksRepository.class);

        PolicyV1_StdDevRelative policy = new PolicyV1_StdDevRelative();
        PolicyRegistry registry = new PolicyRegistry(List.of(policy));

//        marksService = new MarksService(marksRepository, registry);
    }

    @Test
    void should_apply_stddev_policy_and_assign_grades() {

        SubjectMarks_GradingRequest request =
                new SubjectMarks_GradingRequest("CSE2023", "MATH101", null);

        when(marksRepository
                .findByEnrollment_Student_ClassCodeAndEnrollment_Subject_SubjectCodeAndMarksTypeIn(
                        Mockito.any(), Mockito.any(), Mockito.any()
                )
        ).thenReturn(sampleMarks());

        SubjectMarks_GradingResponse response = marksService.getGrading(request);

        assertNotNull(response);
        assertEquals(5, response.students().size());

        boolean foundFail = false;
        boolean foundTopGrade = false;

        for (var s : response.students()) {
            assertNotNull(s.getGrade(), "Grade must be assigned by policy");

            if (s.getTotal().compareTo(BigDecimal.valueOf(35)) < 0) {
                assertEquals(GradeLetter.F, s.getGrade());
                foundFail = true;
            }

            if (s.getTotal().compareTo(BigDecimal.valueOf(85)) >= 0) {
                assertNotEquals(GradeLetter.F, s.getGrade(), "High scorer must not fail");
                foundTopGrade = true;
            }
        }

        assertTrue(foundFail, "At least one student must fail");
        assertTrue(foundTopGrade, "At least one student must get top grade");
    }

    // ---------- helpers ----------

    private List<Mark> sampleMarks() {
        return List.of(
                mark("21CS001", 90),
                mark("21CS002", 80),
                mark("21CS003", 70),
                mark("21CS004", 40),
                mark("21CS005", 20)
        );
    }

    private Mark mark(String roll, int total) {
        Student student = new Student();
        student.setId(UUID.randomUUID());
        student.setRollNumber(roll);

        AcademicSession session = new AcademicSession();
        session.setGradingPolicyVer("V1_STDDEV");

        StudentEnrollment enrollment = new StudentEnrollment();
        enrollment.setStudent(student);
        enrollment.setSession(session);

        Mark m = new Mark();
        m.setEnrollment(enrollment);
        m.setMarksType(MarkComponentType.INTERNAL);
        m.setMarks(BigDecimal.valueOf(total));

        return m;
    }
}
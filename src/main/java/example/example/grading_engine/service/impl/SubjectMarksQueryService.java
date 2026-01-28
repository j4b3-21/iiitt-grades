package example.example.grading_engine.service.impl;

import example.example.grading_engine.dto.StudentMarksPerSubjectDTO;
import example.example.grading_engine.repository.StudentMarksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectMarksQueryService {

    private final StudentMarksRepository studentMarksRepository;

    public SubjectMarksQueryService(
            StudentMarksRepository studentMarksRepository
    ) {
        this.studentMarksRepository = studentMarksRepository;
    }

    public List<StudentMarksPerSubjectDTO> getClassMarksForSubject(
            String subjectCode,
            Integer semester,
            String academicYear
    ) {
        return studentMarksRepository.fetchTotalMarksForSubject(
                subjectCode,
                academicYear,
                semester
        );
    }

}

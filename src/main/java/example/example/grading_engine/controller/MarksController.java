package example.example.grading_engine.controller;

import example.example.grading_engine.dto.GradingResult;
import example.example.grading_engine.dto.SubjectMarks_GradingRequest;
import example.example.grading_engine.service.impl.MarksService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/faculty/marks")
public class MarksController {


    private final MarksService marksService;

    public MarksController(MarksService marksService) {
        this.marksService = marksService;
    }


    @PostMapping("/getGrading")
    public ResponseEntity<GradingResult> getGrading(
            @Valid @RequestBody SubjectMarks_GradingRequest request
    ) {

        GradingResult response = marksService.getGrading(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

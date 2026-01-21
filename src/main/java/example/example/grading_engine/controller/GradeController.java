package example.example.grading_engine.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    @PostMapping
    public String submitGrades(
            @RequestParam("semester") String semester,
            @RequestParam("courseCode") String courseCode,
            @RequestParam("subjectId") Long subjectId) {

        return String.format("semester=%s, courseCode=%s, subjectId=%d",
                semester, courseCode, subjectId);
    }

}

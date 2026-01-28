package example.example.grading_engine.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GradeController {

    @GetMapping("/")
    public String getGrades() {
        return "Grades endpoint is working!";
    }

    @PostMapping("/findClass")
    public String submitGrades(
            @RequestParam("semester") String semester,
            @RequestParam("courseCode") String courseCode,
            @RequestParam("subjectId") int subjectId) {

        return String.format("semester=%s, courseCode=%s, subjectId=%d",
                semester, courseCode, subjectId);
    }

}

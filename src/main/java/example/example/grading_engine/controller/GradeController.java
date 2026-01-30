package example.example.grading_engine.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")

public class GradeController {

    @GetMapping("/")
    public String loginSuccess() {
        return "Login Successful";
    }

    @PostMapping()
    public String submitGrades(
            @RequestParam("semester") String semester,
            @RequestParam("courseCode") String courseCode,
            @RequestParam("subjectId") Long subjectId) {

        return String.format("semester=%s, courseCode=%s, subjectId=%d",
                semester, courseCode, subjectId);
    }




}

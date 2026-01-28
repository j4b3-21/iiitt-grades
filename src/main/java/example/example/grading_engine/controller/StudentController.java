package example.example.grading_engine.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/view")
    public ResponseEntity<String> view() {
        return ResponseEntity.ok("Student access granted");
    }
}
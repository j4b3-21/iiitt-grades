package example.example.grading_engine.controller;

import example.example.grading_engine.dto.TeacherRegisterRequest;
import example.example.grading_engine.service.impl.TeacherRegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/teacher")
public class TeacherAuthController {

    private final TeacherRegistrationService service;

    public TeacherAuthController(TeacherRegistrationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerTeacher(
            @Valid @RequestBody TeacherRegisterRequest request) {

        service.registerTeacher(request);
        return ResponseEntity.ok("Registration submitted. Await HOD approval.");
    }
}
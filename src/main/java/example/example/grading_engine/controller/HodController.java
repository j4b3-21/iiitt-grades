package example.example.grading_engine.controller;

import example.example.grading_engine.enums.userauthentication.UserRole;
import example.example.grading_engine.service.impl.HodApprovalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/hod")
public class HodController {

    private final HodApprovalService hodApprovalService;

    public HodController(HodApprovalService hodApprovalService) {
        this.hodApprovalService = hodApprovalService;
    }

    @PostMapping("/approve-teacher/{userId}")
    public ResponseEntity<String> approveTeacher(
            @PathVariable UUID userId,
            @RequestParam UserRole role) {

        hodApprovalService.approveTeacher(userId, role);
        return ResponseEntity.ok("Teacher approved successfully");
    }
}
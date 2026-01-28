package example.example.grading_engine.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * 🔐 Returns details of currently logged-in user
     * Used to:
     *  - test JWT
     *  - identify role (STUDENT / FACULTY / HOD)
     *  - frontend role-based UI
     */
    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> me(Authentication authentication) {

        String email = authentication.getName();

        // Extract role cleanly (ROLE_FACULTY → FACULTY)
        String role = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("UNKNOWN")
                .replace("ROLE_", "");

        return ResponseEntity.ok(
                Map.of(
                        "email", email,
                        "role", role
                )
        );
    }
}
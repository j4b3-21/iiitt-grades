package example.example.grading_engine.controller;

import example.example.grading_engine.model.entity.User;
import example.example.grading_engine.repository.UserRepository;
import example.example.grading_engine.security.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(
            @CookieValue(name = "REFRESH_TOKEN", required = false) String refreshToken,
            HttpServletResponse response
    ) {
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Claims claims = jwtUtil.extractClaims(refreshToken);
        String userId = claims.getSubject();

        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow();

        String newAccessToken = jwtUtil.generateAccessToken(
                user.getId().toString(),
                user.getRole()
        );

        ResponseCookie accessCookie = ResponseCookie.from("ACCESS_TOKEN", newAccessToken)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/")
                .maxAge(Duration.ofMinutes(15))
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {

        ResponseCookie clearAccess =
                ResponseCookie.from("ACCESS_TOKEN", "")
                        .httpOnly(true)
                        .secure(true)
                        .sameSite("None")
                        .path("/")
                        .maxAge(0)
                        .build();

        ResponseCookie clearRefresh =
                ResponseCookie.from("REFRESH_TOKEN", "")
                        .httpOnly(true)
                        .secure(true)
                        .sameSite("None")
                        .path("/auth/refresh")
                        .maxAge(0)
                        .build();

        response.addHeader(HttpHeaders.SET_COOKIE, clearAccess.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, clearRefresh.toString());

        return ResponseEntity.ok().build();
    }


    @GetMapping("/me")
    public Map<String, Object> getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            return Map.of("authenticated", false);
        }
        // Try to extract userId and role
        Object principal = auth.getPrincipal();
        String userId = principal != null ? principal.toString() : null;
        String role = auth.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse(null);
        assert userId != null;
        assert role != null;
        return Map.of(
                "authenticated", true,
                "userId", userId,
                "role", role
        );
    }
}


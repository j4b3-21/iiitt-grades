package example.example.grading_engine.security;

import example.example.grading_engine.model.entity.User;
import example.example.grading_engine.repository.UserRepository;
import example.example.grading_engine.service.impl.AuditLogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component

public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuditLogService auditLogService;

    public OAuthSuccessHandler(
            UserRepository userRepository,
            JwtUtil jwtUtil,
            AuditLogService auditLogService
    ) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.auditLogService = auditLogService;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not registered"));

        if (!user.getActive()) {
            auditLogService.logLoginFailure(email);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "User inactive");
            return;
        }

        // 🔐 Generate JWT
        String jwt = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole()
        );

        // ✅ ADDITION 1: Print JWT in backend console
        System.out.println("=====================================");
        System.out.println("JWT TOKEN = " + jwt);
        System.out.println("ROLE      = " + user.getRole());
        System.out.println("=====================================");

        auditLogService.logLoginSuccess(user.getId());

        // ✅ ADDITION 2: Keep redirect (unchanged)
        response.sendRedirect(
                "http://localhost:3000/login-success?token=" + jwt
        );
    }
}
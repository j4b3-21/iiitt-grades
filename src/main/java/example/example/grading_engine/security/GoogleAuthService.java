package example.example.grading_engine.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import example.example.grading_engine.model.entity.User;
import example.example.grading_engine.repository.UserRepository;
import example.example.grading_engine.service.impl.AuditLogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GoogleAuthService {

    private final UserRepository userRepository;
    private final AuditLogService auditLogService;

    @Value("${google.oauth.client-id}")
    private String clientId;

    public GoogleAuthService(UserRepository userRepository,
                             AuditLogService auditLogService) {
        this.userRepository = userRepository;
        this.auditLogService = auditLogService;
    }

    public User authenticate(String idTokenString) {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance()
        ).setAudience(Collections.singletonList(clientId))
                .build();

        GoogleIdToken idToken;

        try {
            idToken = verifier.verify(idTokenString);
        } catch (Exception e) {
            auditLogService.logLoginFailure(null);
            throw new RuntimeException("Invalid Google ID token");
        }

        if (idToken == null) {
            auditLogService.logLoginFailure(null);
            throw new RuntimeException("Invalid Google ID token");
        }

        String email = idToken.getPayload().getEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    auditLogService.logLoginFailure(email);
                    return new RuntimeException("User not registered");
                });

        if (!user.getActive()) {
            auditLogService.logLoginFailure(email);
            throw new RuntimeException("User not approved or inactive");
        }

        auditLogService.logLoginSuccess(user.getId());
        return user;
    }
}

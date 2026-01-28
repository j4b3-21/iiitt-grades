package example.example.grading_engine.service.impl;

import example.example.grading_engine.enums.userauthentication.UserRole;
import example.example.grading_engine.model.entity.User;
import example.example.grading_engine.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HodApprovalService {

    private final UserRepository userRepository;

    public HodApprovalService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void approveTeacher(UUID userId, UserRole finalRole) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(finalRole);
        user.setActive(true);

        userRepository.save(user);
    }
}

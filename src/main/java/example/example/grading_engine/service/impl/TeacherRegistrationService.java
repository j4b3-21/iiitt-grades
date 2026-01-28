package example.example.grading_engine.service.impl;

import example.example.grading_engine.dto.TeacherRegisterRequest;
import example.example.grading_engine.enums.academicstructure.AcademicStatus;
import example.example.grading_engine.enums.userauthentication.AuthProvider;
import example.example.grading_engine.enums.userauthentication.UserRole;
import example.example.grading_engine.model.entity.User;
import example.example.grading_engine.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TeacherRegistrationService {

    private static final String ALLOWED_DOMAIN = "@iiitt.ac.in";

    private final UserRepository userRepository;

    public TeacherRegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerTeacher(TeacherRegisterRequest request) {

        String email = request.getEmail().toLowerCase();

        if (!email.endsWith(ALLOWED_DOMAIN)) {
            throw new RuntimeException("Only @iiitt.ac.in email allowed");
        }

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("User already exists");
        }

        if (request.getRole() != UserRole.FACULTY &&
                request.getRole() != UserRole.HOD) {
            throw new RuntimeException("Invalid role");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(email);
        user.setRole(request.getRole());
        user.setAuthProvider(AuthProvider.GOOGLE);
        user.setActive(false); // needs approval
        user.setAcademicStatus(AcademicStatus.ACTIVE);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
    }
}
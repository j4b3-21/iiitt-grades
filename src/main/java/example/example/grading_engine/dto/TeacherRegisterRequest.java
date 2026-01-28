package example.example.grading_engine.dto;

import example.example.grading_engine.enums.userauthentication.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TeacherRegisterRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email
    @NotBlank(message = "Email is required")
    private String email;

    @NotNull(message = "Role is required")
    private UserRole role;   // FACULTY or HOD

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
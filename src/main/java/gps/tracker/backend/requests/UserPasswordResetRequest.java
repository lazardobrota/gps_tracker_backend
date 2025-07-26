package gps.tracker.backend.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserPasswordResetRequest {
    @NotBlank
    @NotNull
    private String oldPassword;
    @NotBlank
    @NotNull
    private String newPassword;
}

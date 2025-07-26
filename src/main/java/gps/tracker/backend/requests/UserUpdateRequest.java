package gps.tracker.backend.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Getter
public class UserUpdateRequest {

    @NotBlank
    @NotNull
    private String firstName;
    @NotBlank
    @NotNull
    private String lastName;
    @NotBlank
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String password;
}

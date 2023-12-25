package spring.belajarspringrestfulapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequest {
    @Size(max = 100)
    @NotBlank(message = "username is required")
    private String username;

    @Size(max = 100)
    @NotBlank(message = "password is required")
    private String password;

    @Size(max = 100)
    @NotBlank(message = "name is required")
    private String name;
}

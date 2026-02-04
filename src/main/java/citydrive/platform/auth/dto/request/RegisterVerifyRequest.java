package citydrive.platform.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterVerifyRequest(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String otp

) {

}

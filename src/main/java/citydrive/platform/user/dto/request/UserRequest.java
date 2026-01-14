package citydrive.platform.user.dto.request;

import citydrive.platform.user.enums.City;
import citydrive.platform.user.enums.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserRequest(
        @NotBlank
        @Size(min = 4, max = 30)
        @Pattern(regexp = "^[A-Za-zА-Яа-яёЁ ]+$")
        String fullName,

        @NotNull(message = "Language is required")
        Gender gender,

        @NotBlank(message = "Phone number cannot be empty")
        @Pattern(regexp = "^\\+[1-9]\\d{7,14}$")
        String phoneNumber,

        @NotNull(message = "Language is required")
        City city,

        @NotNull(message = "Birth date cannot be null")
        @Past(message = "Birthdate must be in the past")
        LocalDate birthDate,

        @NotBlank(message = "Password cannot be empty")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).*$")
        String password
) {

}

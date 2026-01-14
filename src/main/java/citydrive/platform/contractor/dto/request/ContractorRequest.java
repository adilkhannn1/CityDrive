package citydrive.platform.contractor.dto.request;

import citydrive.platform.user.enums.City;
import citydrive.platform.user.enums.Gender;
import citydrive.platform.user.enums.Language;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ContractorRequest(
        @NotBlank
        @Size(min = 4, max = 30)
        @Pattern(regexp = "^[A-Za-zА-Яа-яёЁ ]+$")
        String fullName,

        @NotNull(message = "Language is required")
        City city,

        @NotBlank(message = "Birth date is required")
        @Past(message = "Birthdate must be in the past")
        LocalDate birthDate,

        @NotNull(message = "Language is required")
        Gender gender,

        @NotBlank(message = "avatarUrlIsRequired")
        String avatarUrl,

        @NotNull(message = "Language is required")
        Language language,

        @NotNull
        String companyAddress,

        @NotNull
        String urlCompanyRegister,

        @NotBlank(message = "Password cannot be empty")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).*$")
        String password

) {
}

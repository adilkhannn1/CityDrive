package citydrive.platform.user.dto.request;

import citydrive.platform.user.enums.City;
import citydrive.platform.user.enums.Gender;
import citydrive.platform.user.enums.Language;
import jakarta.validation.constraints.*;

import java.time.LocalDate;


public record UserProfileRequest(

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
         Language language

) {


}

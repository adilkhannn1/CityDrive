package citydrive.platform.user.dto.response;

import citydrive.platform.user.enums.City;
import citydrive.platform.user.enums.Gender;
import citydrive.platform.user.enums.Language;

import java.time.LocalDate;

public record UserProfileResponse(
        String fullName,
        City city,
        LocalDate birthDate,
        Gender gender,
        String avatarUrl,
        Language language
) {

}

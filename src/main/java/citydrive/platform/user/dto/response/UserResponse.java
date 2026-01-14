package citydrive.platform.user.dto.response;

import citydrive.platform.user.enums.City;
import citydrive.platform.user.enums.Gender;

import java.time.LocalDate;

public record UserResponse(
        Long id,
        String fullName,
        Gender gender,
        String phoneNumber,
        City city,
        LocalDate birthDate
) {

}

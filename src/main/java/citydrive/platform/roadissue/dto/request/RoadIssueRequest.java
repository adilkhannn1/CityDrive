package citydrive.platform.roadissue.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record RoadIssueRequest(
        @NotBlank
        String photoPath,
        @NotBlank
        String title,
        String description,
        @NotNull
        Double latitude,
        @NotNull
        Double longitude
) {

}

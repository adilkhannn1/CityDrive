package citydrive.platform.roadissue.dto.request;


import citydrive.platform.files.entity.FileEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record RoadIssueRequest(
        @NotBlank
        FileEntity file,
        @NotBlank
        String title,
        String description,
        @NotNull
        Double latitude,
        @NotNull
        Double longitude
) {

}

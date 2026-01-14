package citydrive.platform.roadissue.dto.response;

import citydrive.platform.roadissue.Enums.Status;

import java.time.LocalDateTime;

public record RoadIssueResponse (
        Long id,
        String photoPath,
        String title,
        String description,
        Status status,
        Double latitude,
        Double longitude,
        LocalDateTime createdAt
){

}

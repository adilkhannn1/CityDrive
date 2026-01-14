package citydrive.platform.roadissue.dto.response;

import citydrive.platform.roadissue.Enums.Priority;
import citydrive.platform.roadissue.Enums.Status;

import java.time.LocalDateTime;

public record RoadIssuePreviewResponse(
        Long id,
        String title,
        Status status,
        Priority priority,
        String description,
        LocalDateTime createdAt
) {

}

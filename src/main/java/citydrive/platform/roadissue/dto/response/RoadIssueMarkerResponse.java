package citydrive.platform.roadissue.dto.response;

import citydrive.platform.roadissue.Enums.Priority;

public record RoadIssueMarkerResponse(
        Long id,
        Double latitude,
        Double longitude,
        Priority priority
) {

}

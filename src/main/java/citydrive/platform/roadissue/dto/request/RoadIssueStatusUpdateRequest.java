package citydrive.platform.roadissue.dto.request;

import citydrive.platform.roadissue.Enums.Status;

public record RoadIssueStatusUpdateRequest(
        Status status,
        String adminComment
) {


}

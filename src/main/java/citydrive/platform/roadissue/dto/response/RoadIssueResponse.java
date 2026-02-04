package citydrive.platform.roadissue.dto.response;

import citydrive.platform.files.entity.FileEntity;
import citydrive.platform.roadissue.Enums.Status;

import java.time.LocalDateTime;

public record RoadIssueResponse (
        Long id,
        FileEntity file,
        String title,
        String description,
        Status status,
        Double latitude,
        Double longitude,
        LocalDateTime createdAt
){

}

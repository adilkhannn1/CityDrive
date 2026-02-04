package citydrive.platform.roadissue.mapper;
import citydrive.platform.roadissue.dto.request.RoadIssueRequest;
import citydrive.platform.roadissue.dto.response.RoadIssueMarkerResponse;
import citydrive.platform.roadissue.dto.response.RoadIssuePreviewResponse;
import citydrive.platform.roadissue.dto.response.RoadIssueResponse;
import citydrive.platform.roadissue.entity.RoadIssueEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoadIssueMapper {


    public RoadIssueResponse toRoadIssue(RoadIssueEntity roadIssueEntity){
        return new RoadIssueResponse(
                roadIssueEntity.getId(),
                roadIssueEntity.getFile(),
                roadIssueEntity.getTitle(),
                roadIssueEntity.getDescription(),
                roadIssueEntity.getStatus(),
                roadIssueEntity.getLatitude(),
                roadIssueEntity.getLongitude(),
                roadIssueEntity.getCreatedAt()
        );
    }

    public RoadIssueEntity toEntity(RoadIssueRequest roadIssueRequest){
        return new RoadIssueEntity(
                roadIssueRequest.file(),
                roadIssueRequest.title(),
                roadIssueRequest.description(),
                roadIssueRequest.latitude(),
                roadIssueRequest.longitude()
        );
    }

    public List<RoadIssueResponse> toRoadIssueList(List<RoadIssueEntity> entities){
        return entities.stream().map(entity -> new RoadIssueResponse(
                entity.getId(),
                entity.getFile(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getCreatedAt()
        )).toList();
    }

    public List<RoadIssueMarkerResponse> toRoadIssueMarkerList(List<RoadIssueEntity> entities){
        return entities.stream().map(entity -> new RoadIssueMarkerResponse(
                entity.getId(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getPriority()

        )).toList();
    }

    public RoadIssuePreviewResponse toRoadIssuePreview(RoadIssueEntity entity){
        return new RoadIssuePreviewResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getStatus(),
                entity.getPriority(),
                entity.getDescription(),
                entity.getCreatedAt()
        );
    }

}

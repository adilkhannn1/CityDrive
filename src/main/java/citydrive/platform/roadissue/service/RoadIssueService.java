package citydrive.platform.roadissue.service;

import citydrive.platform.roadissue.dto.request.RoadIssueRequest;
import citydrive.platform.roadissue.dto.request.RoadIssueStatusUpdateRequest;
import citydrive.platform.roadissue.dto.response.RoadIssueMarkerResponse;
import citydrive.platform.roadissue.dto.response.RoadIssuePreviewResponse;
import citydrive.platform.roadissue.dto.response.RoadIssueResponse;
import citydrive.platform.roadissue.entity.RoadIssueEntity;
import citydrive.platform.roadissue.mapper.RoadIssueMapper;
import citydrive.platform.roadissue.repository.RoadIssueRepository;
import citydrive.platform.security.service.CurrentUserService;
import citydrive.platform.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RoadIssueService {
    private final RoadIssueRepository roadIssueRepository;
    private final RoadIssueMapper roadIssueMapper;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;


    public RoadIssueResponse createRoadIssue(RoadIssueRequest roadIssueRequest){
        String email = currentUserService.getCurrentUserEmail();
        var user = userRepository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found by this id"));
        var toEntity = roadIssueMapper.toEntity(roadIssueRequest);
        toEntity.setCreator(user);
        toEntity.setCreatedAt(LocalDateTime.now());
        roadIssueRepository.save(toEntity);
        return roadIssueMapper.toRoadIssue(toEntity);
    }

    public List<RoadIssueMarkerResponse> getAllRoadIssueMarkers(){
        var entities = roadIssueRepository.findAll();
        return roadIssueMapper.toRoadIssueMarkerList(entities);
    }


    public RoadIssueResponse updateRoadIssue(Long id, RoadIssueRequest updatedIssue){
        var roadEntity = roadIssueRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "RoadIssue not found by this id!"));
        roadEntity.setFile(updatedIssue.file());
        roadEntity.setDescription(updatedIssue.description());
        roadEntity.setLatitude(updatedIssue.latitude());
        roadEntity.setLongitude(updatedIssue.longitude());
        roadIssueRepository.save(roadEntity);
        return roadIssueMapper.toRoadIssue(roadEntity);
    }

    public void updateRoadStatus(Long id, RoadIssueStatusUpdateRequest roadIssueStatusUpdateRequest){
        var entity = roadIssueRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "RoadIssue mapper is not found by this id"));
        entity.setStatus(roadIssueStatusUpdateRequest.status());
        entity.setAdminComment(roadIssueStatusUpdateRequest.adminComment());
        roadIssueRepository.save(entity);
    }

    public void deleteRoadIssue(Long id){
        RoadIssueEntity roadEntity = roadIssueRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "RoadIssue is not found by this id"));
        roadIssueRepository.delete(roadEntity);
    }

    public RoadIssueResponse getRoadIssue(Long id){
       var entity = roadIssueRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "RoadIssue is not found by this id!"));
        return roadIssueMapper.toRoadIssue(entity);
    }

    public RoadIssuePreviewResponse getRoadIssuePreview(Long id){
        var entity = roadIssueRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "RoadIssue is not found by this id!"));
        return roadIssueMapper.toRoadIssuePreview(entity);
    }

}

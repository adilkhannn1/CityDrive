package citydrive.platform.user.controller;


import citydrive.platform.roadissue.dto.request.RoadIssueRequest;
import citydrive.platform.roadissue.dto.response.RoadIssueMarkerResponse;
import citydrive.platform.roadissue.dto.response.RoadIssuePreviewResponse;
import citydrive.platform.roadissue.dto.response.RoadIssueResponse;
import citydrive.platform.roadissue.service.RoadIssueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/road-issue")
public class UserRoadIssueController {


    private final RoadIssueService roadIssueService;

    public UserRoadIssueController(RoadIssueService roadIssueService) {
        this.roadIssueService = roadIssueService;
    }


    @PostMapping()
    public ResponseEntity<RoadIssueResponse> createRoadIssue(@RequestBody @Valid RoadIssueRequest roadIssueRequest){
        var roadIssue = roadIssueService.createRoadIssue(roadIssueRequest);
        return ResponseEntity.ok(roadIssue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoadIssue(@PathVariable  Long id){
        roadIssueService.deleteRoadIssue(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<RoadIssueMarkerResponse>> getAllMarkers(){
        return ResponseEntity.ok(roadIssueService.getAllRoadIssueMarkers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoadIssueResponse> getRoadIssue(@PathVariable Long id){
        return ResponseEntity.ok(roadIssueService.getRoadIssue(id));
    }

    @GetMapping("/{id}/preview")
    public ResponseEntity<RoadIssuePreviewResponse> getRoadPreview(@PathVariable Long id){
        return ResponseEntity.ok(roadIssueService.getRoadIssuePreview(id));
    }




}

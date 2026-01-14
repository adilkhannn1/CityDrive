package citydrive.platform.roadissue.controller;

import citydrive.platform.roadissue.dto.request.RoadIssueRequest;
import citydrive.platform.roadissue.dto.response.RoadIssueMarkerResponse;
import citydrive.platform.roadissue.dto.response.RoadIssueResponse;
import citydrive.platform.roadissue.service.RoadIssueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/road-issues")
public class RoadIssueController {

    private final RoadIssueService roadIssueService;

    public RoadIssueController(RoadIssueService roadIssueService){
        this.roadIssueService = roadIssueService;
    }







    @PutMapping("/{id}")
    public ResponseEntity<RoadIssueResponse> updateRoadIssue(@PathVariable Long id, @RequestBody RoadIssueRequest updatedIssues){
        var roadIssue = roadIssueService.updateRoadIssue(id, updatedIssues);
        return ResponseEntity.ok(roadIssue);
    }


}


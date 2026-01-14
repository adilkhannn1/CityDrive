package citydrive.platform.admin.controller;

import citydrive.platform.roadissue.dto.request.RoadIssueStatusUpdateRequest;
import citydrive.platform.roadissue.dto.response.RoadIssueResponse;
import citydrive.platform.roadissue.service.RoadIssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins/road-issue")
public class AdminRoadIssueController {

    private final RoadIssueService roadIssueService;

    public AdminRoadIssueController(RoadIssueService roadIssueService) {
        this.roadIssueService = roadIssueService;
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RoadIssueResponse> updateRoadIssueStatus(@PathVariable Long id,
                                                                   @RequestBody RoadIssueStatusUpdateRequest roadIssueStatusUpdateRequest
    ){
        roadIssueService.updateRoadStatus(id, roadIssueStatusUpdateRequest);
       return ResponseEntity.noContent().build();
    }


}

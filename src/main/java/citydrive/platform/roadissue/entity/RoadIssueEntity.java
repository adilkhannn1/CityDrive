package citydrive.platform.roadissue.entity;

import citydrive.platform.files.entity.FileEntity;
import citydrive.platform.roadissue.Enums.Priority;
import citydrive.platform.roadissue.Enums.Status;
import citydrive.platform.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "road_issues")
@Data
public class RoadIssueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private FileEntity file;

    private String title;

    private String description;
    private Double latitude;
    private Double longitude;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING_REVIEW;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserEntity creator;

    private String adminComment;

    public RoadIssueEntity(){
    }

    public RoadIssueEntity(
            FileEntity file,
            String title,
            String description,
            Double latitude,
            Double longitude
    ){
        this.file = file;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }



}

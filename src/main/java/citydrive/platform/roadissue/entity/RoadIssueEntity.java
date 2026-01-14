package citydrive.platform.roadissue.entity;

import citydrive.platform.roadissue.Enums.Priority;
import citydrive.platform.roadissue.Enums.Status;
import citydrive.platform.user.entity.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "road_issues")
public class RoadIssueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String photoPath;
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
            String photoPath,
            String title,
            String description,
            Double latitude,
            Double longitude
    ){
        this.photoPath = photoPath;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getDescription() {
        return description;
    }

        public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }


    public String getAdminComment(){
        return adminComment;
    }

    public void setAdminComment(String adminComment){
        this.adminComment = adminComment;
    }

}

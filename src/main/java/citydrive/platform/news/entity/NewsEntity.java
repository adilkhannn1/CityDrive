package citydrive.platform.news.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String photoPath;
    private LocalDateTime createdAt = LocalDateTime.now(); // дата публикации


    public NewsEntity(String title, String description, String photoPath) {
        this.title = title;
        this.description = description;
        this.photoPath = photoPath;
    }


    public NewsEntity() {

    }

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPhotoPath() { return photoPath; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }


}


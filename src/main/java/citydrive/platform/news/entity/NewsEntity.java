package citydrive.platform.news.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "news")
@Data
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

}


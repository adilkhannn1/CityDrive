package citydrive.platform.files.entity;
import citydrive.platform.files.enums.FileStatus;
import citydrive.platform.user.entity.UserEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;
    String objectKey;
    String contentType;
    Long sizeBytes;
    @Enumerated(EnumType.STRING)
    FileStatus fileStatus;
    LocalDateTime createdAt;
}




package citydrive.platform.security.refresh.entity;

import citydrive.platform.user.entity.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "RefreshTokens")
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private String token;
    private LocalDateTime expiryDate;
    private Boolean revoked;

}

package citydrive.platform.news.dto.Response;

import java.time.LocalDateTime;

public record NewsResponse(
        Long id,
        String title,
        String description,
        String photoPath,
        LocalDateTime createdAt

) {
}

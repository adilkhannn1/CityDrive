package citydrive.platform.news.dto.Request;


public record NewsRequest(
        String title,
        String description,
        String photoPath
) {

}

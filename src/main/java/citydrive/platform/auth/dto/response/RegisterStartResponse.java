package citydrive.platform.auth.dto.response;

public record RegisterStartResponse(
        String message,
        String email,
        int expirationSeconds
) {

}

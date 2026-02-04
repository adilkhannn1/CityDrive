package citydrive.platform.auth.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}

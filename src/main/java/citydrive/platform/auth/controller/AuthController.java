package citydrive.platform.auth.controller;
import citydrive.platform.auth.dto.request.LoginRequest;
import citydrive.platform.auth.dto.request.RefreshTokenRequest;
import citydrive.platform.auth.dto.request.RegisterVerifyRequest;
import citydrive.platform.auth.dto.response.AuthResponse;
import citydrive.platform.auth.dto.response.RegisterStartResponse;
import citydrive.platform.auth.service.AuthService;
import citydrive.platform.user.dto.request.UserRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterStartResponse> startRegister(@RequestBody @Valid UserRequest userRequest){
        var response = authService.initiateRegistration(userRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/verify")
    public ResponseEntity<AuthResponse> verifyOtp(@RequestBody RegisterVerifyRequest registerVerifyRequest){
        var tokens =  authService.verifyRegistration(registerVerifyRequest.email(), registerVerifyRequest.otp());
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        var tokens = authService.refreshAccessToken(refreshTokenRequest.refreshToken());
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        var tokens = authService.login(loginRequest);
        return ResponseEntity.ok(tokens);
    }
    

}

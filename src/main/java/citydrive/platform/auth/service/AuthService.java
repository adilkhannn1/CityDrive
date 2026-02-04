package citydrive.platform.auth.service;
import citydrive.platform.auth.dto.request.LoginRequest;
import citydrive.platform.auth.dto.response.AuthResponse;
import citydrive.platform.common.notification.service.EmailService;
import citydrive.platform.auth.dto.response.RegisterStartResponse;
import citydrive.platform.refresh.service.RefreshTokenService;
import citydrive.platform.security.service.JwtService;
import citydrive.platform.user.dto.request.UserRequest;
import citydrive.platform.user.entity.UserEntity;
import citydrive.platform.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final OtpService otpService;
    private final EmailService emailService;
    private final RegistrationCacheService registrationCacheService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public RegisterStartResponse initiateRegistration(UserRequest userRequest){

        log.info("Starting registration for email: {}", userRequest.email());

        if(userService.existsByEmail(userRequest.email())){
            log.warn("Registration failed: email already exists:  {} ", userRequest.email());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already registered!");
        }

        registrationCacheService.saveUserCache(userRequest);
        log.debug("User data cashed for email: {}", userRequest.email());

        var otp = otpService.generateCode();

        otpService.saveOtp(userRequest.email(), otp);
        log.debug("OTP generated and saved for email: {}", userRequest.email());

        emailService.sendOtpEmail(userRequest.email(), otp);
        log.info("OTP email sent to: {}", userRequest.email());

        return new RegisterStartResponse(
                "Код подтверждение отправлен на " + userRequest.email(),
                userRequest.email(),
                120
        );
    }

    @Transactional
    public AuthResponse verifyRegistration(String email, String otp){
        var isOtpValid = otpService.verifyOtp(email, otp);
        if(!isOtpValid){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your code is incorrect!");
        }
        var userData = registrationCacheService.getUserCache(email);
        if(userData == null){
            log.warn("Registration session expired for email: {}", email);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Registration session expired!");
        }
        var userEntity = userService.createUser(userData);
        registrationCacheService.deleteUserCache(email);

        otpService.deleteOpt(email);

        String accessToken = jwtService.generateAccessToken(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getRole().name()
        );

        var refreshToken = refreshTokenService.createRefreshToken(userEntity);
        log.debug("Refresh token generated for user: {}", userEntity.getId());
        log.info("Registration completed successfully for user: {}", userEntity.getId());

        return AuthResponse.
                builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(900L)
                .tokenType("Bearer")
                .message("Success").
                 build();
    }

    public AuthResponse refreshAccessToken(String refreshToken){
        UserEntity user = refreshTokenService.validateToken(refreshToken);
        String newAccess = jwtService.generateAccessToken(user.getId(), user.getEmail(), user.getRole().name());
        return new AuthResponse(newAccess, refreshToken, "Bearer", 100000L, "Success");
    }

    @Transactional
    public AuthResponse login(LoginRequest loginRequest){
            UserEntity user = userService.getUserByEmail(loginRequest.email());
            var hashedPassword = passwordEncoder.encode(loginRequest.password());
            if(!hashedPassword.equals(user.getPassword())){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or password is incorrect!");
            }
            var accessToken = jwtService.generateAccessToken(user.getId(), user.getEmail(), user.getRole().name());
            var refreshToken = refreshTokenService.createRefreshToken(user);
            return new AuthResponse(accessToken, refreshToken, "Bearer ", 900L, "You are logged in!");
    }

}

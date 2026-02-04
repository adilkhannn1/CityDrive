package citydrive.platform.refresh.service;

import citydrive.platform.refresh.entity.RefreshTokenEntity;
import citydrive.platform.refresh.repository.RefreshTokenRepository;
import citydrive.platform.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${app.refresh-token.expiration-days}")
    private int refreshTokenExpirationDate;

    @Transactional
    public String createRefreshToken(UserEntity user){
        RefreshTokenEntity refreshTokenEntity =  RefreshTokenEntity.builder()
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusDays(refreshTokenExpirationDate))
                .user(user)
                .revoked(false).
                build();
        refreshTokenRepository.save(refreshTokenEntity);
        return refreshTokenEntity.getToken();
    }

    public UserEntity validateToken(String token){

        RefreshTokenEntity refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not found!"));
        if(refreshToken.getRevoked()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Refresh token revoked!");
        }
        if(refreshToken.getExpiryDate().isBefore(LocalDateTime.now())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Refresh token expired!");
        }
        return refreshToken.getUser();
    }

}

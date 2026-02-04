package citydrive.platform.security.service;


import citydrive.platform.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtTokenProvider jwtTokenProvider;

    public String generateAccessToken(Long userId, String email, String role){
        log.debug("Generating access token for user: {}", userId);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("role", role);
        String token = jwtTokenProvider.createToken(claims, email);
        log.debug("Access token generated for user: {}", userId);
        return token;
    }

    public boolean validateToken(String token){
        return jwtTokenProvider.validateToken(token);
    }

    public String getEmailFromToken(String token){
        return jwtTokenProvider.getSubject(token);
    }

    public Long getUserIdFromToken(String token){
        return jwtTokenProvider.getClaims(token).get("userId", Long.class);
    }

    public String getRoleFromToken(String token){
        return jwtTokenProvider.getClaims(token).get("role", String.class);
    }




}

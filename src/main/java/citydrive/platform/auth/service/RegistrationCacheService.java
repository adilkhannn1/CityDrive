package citydrive.platform.auth.service;

import citydrive.platform.user.dto.request.UserRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Service
public class RegistrationCacheService {

    private static final int CACHE_EXPIRATION_MINUTES = 2;
    private static final String KEY_PREFIX = "registration:pending:";
    private final ObjectMapper objectMapper;
    private final StringRedisTemplate redisTemplate;

    public RegistrationCacheService(StringRedisTemplate redisTemplate, ObjectMapper objectMapper){
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public void saveUserCache(
            UserRequest registerStartRequest
    ){
        try {
            String key = KEY_PREFIX + registerStartRequest.email();
            String value = objectMapper.writeValueAsString(registerStartRequest);
            redisTemplate.opsForValue().set(key, value, CACHE_EXPIRATION_MINUTES, TimeUnit.MINUTES);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize registration data", e);
        }
    }

    public UserRequest getUserCache(String email){
        try {
         String key = KEY_PREFIX + email;
         String value = redisTemplate.opsForValue().get(key);
         return objectMapper.readValue(value, UserRequest.class);
     }catch (Exception e){
         throw new RuntimeException("Failed to deserialize registration data", e);
     }

    }

    public void deleteUserCache(String email){
            String key = KEY_PREFIX + email;
            redisTemplate.delete(key);
    }

}

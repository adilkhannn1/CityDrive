package citydrive.platform.auth.service;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {
    private final StringRedisTemplate redisTemplate;

    private static final int OTP_EXPIRATION_MINUTES = 2;

    public OtpService(StringRedisTemplate redisTemplate){
        this.redisTemplate =redisTemplate;
    }


    public String generateCode(){
        SecureRandom random = new SecureRandom();
        int otp = 1000+random.nextInt(9000);
        return String.valueOf(otp);
    }


    public void saveOtp(String email, String otp){
        String key = "otp:"+email;
        redisTemplate.opsForValue().set(key, otp, OTP_EXPIRATION_MINUTES, TimeUnit.MINUTES);
    }


    public boolean verifyOtp(String email, String otp){

        String key = "otp:"+email;
        String savedOtp = redisTemplate.opsForValue().get(key);

        if (savedOtp != null && savedOtp.equals(otp)){
            redisTemplate.delete(key);
            return true;
        }
        return false;
    }


    public void deleteOpt(String email){
        redisTemplate.delete(email);
    }





}


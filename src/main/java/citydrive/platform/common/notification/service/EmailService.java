package citydrive.platform.common.notification.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @Async
    public void sendOtpEmail(String toEmail, String otp){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("adikbudslive@gmail.com");
            message.setTo(toEmail);
            message.setSubject("CityDrive - Код подтверждения регистрации");
            message.setText("Ваш код подтверждения: " + otp + "\n\nКод действителен 2 минут");
            mailSender.send(message);
            System.out.println("Email sent to: "+toEmail);
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}

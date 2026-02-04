package citydrive.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CityDriveApplication {
	public static void main(String[] args) {
		SpringApplication.run(CityDriveApplication.class, args);
	}
}

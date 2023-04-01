package notion.to.social.enduco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EnducoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnducoApplication.class, args);
	}

}

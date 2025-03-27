package deliveryapp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin
@SpringBootApplication
public class DeliveryappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryappApplication.class, args);
	}

}

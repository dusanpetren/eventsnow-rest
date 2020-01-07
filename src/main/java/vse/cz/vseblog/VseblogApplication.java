package vse.cz.vseblog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VseblogApplication {

	private static final Logger logger = LoggerFactory.getLogger(VseblogApplication.class);

	public static void main(String[] args) {
		logger.info("Starting server");
		SpringApplication.run(VseblogApplication.class, args);
	}
}
